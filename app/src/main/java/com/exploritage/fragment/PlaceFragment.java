package com.exploritage.fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.activity.CityDetailActivity;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.model.responses.Datum;
import com.exploritage.model.responses.place.PlaceData;
import com.exploritage.model.responses.place.PlaceToVisitResponse;
import com.exploritage.service.DownloadTextService;
import com.exploritage.service.SaveImageOnExternalStorageService;
import com.exploritage.util.ApiGenerator;
import com.exploritage.util.PicassoUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by ajay on 21/2/17.
 */
public class PlaceFragment extends BaseFragment implements CustomListAdapterInterface, AdapterView.OnItemClickListener {

    private ArrayList<PlaceData> placesList;
    private CustomListAdapter adapter;
    private Datum city;
    private TextView tvNoSubsiteFound;
    private ProgressBar progressBar;


    public static PlaceFragment getInstance(Datum city) {
        PlaceFragment fragment = new PlaceFragment();
        fragment.city = city;
        return fragment;
    }

    @Override
    public void initViews() {
        progressBar = (ProgressBar) findView(R.id.progressbar);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.color_light_green), PorterDuff.Mode.MULTIPLY);
        tvNoSubsiteFound = (TextView) findView(R.id.tv_empty_list);
        placesList = new ArrayList<>();
        ListView listView = (ListView) findView(R.id.lv_place_to_visit);
        adapter = new CustomListAdapter(getActivity(), R.layout.row_places_to_visit, placesList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

//        listView.setEmptyView(tvNoSubsiteFound);
        String objectId = city.getObjectId();
        PlaceToVisitResponse placeToVisitResponse = PlaceToVisitResponse.getInstance(objectId);
        setPlacesList(placeToVisitResponse);


        //Getting updated data.
        if (city != null) {
            getPlaceToVisitApi(city);
        }
    }

    public List<PlaceData> getAllSubsitesData() {
        if (placesList != null && placesList.size() > 0) {
            return placesList;
        }
        return null;
    }

    private void getPlaceToVisitApi(Datum city) {
        String objectId = city.getObjectId();
        if (!TextUtils.isEmpty(objectId)) {
            HttpParamObject httpParamObject = ApiGenerator.getPlace(objectId);
            executeTask(AppConstants.TASK_CODES.PLACES_TO_VISIT, httpParamObject);
        }
    }

    private void savePlaceData(PlaceToVisitResponse placeToVisitResponse) {
        if (placeToVisitResponse != null) {
            String objectId = city.getObjectId();
            Preferences.saveData(AppConstants.PREF_KEYS.KEY_PLACE_TO_VISIT_JSON + objectId, JsonUtil.toJson(placeToVisitResponse));
        }
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);

        if (null == response) {
            showToast(getResources().getString(R.string.no_response));
            return;
        }

        switch (taskCode) {
            case AppConstants.TASK_CODES.PLACES_TO_VISIT:

                PlaceToVisitResponse placeToVisitResponse = (PlaceToVisitResponse) response;
                savePlaceData(placeToVisitResponse);
                setPlacesList(placeToVisitResponse);
                break;
        }
    }


    private void setPlacesList(PlaceToVisitResponse placeToVisitResponse) {
        if (placeToVisitResponse != null) {

            List<PlaceData> dataList = placeToVisitResponse.getData();
            if (dataList.size() == 0) {
                tvNoSubsiteFound.setVisibility(View.VISIBLE);
            }
            placesList.clear();
            placesList.addAll(dataList);
            saveImagesLocally();
            adapter.notifyDataSetChanged();
        }
    }

    private void saveImagesLocally() {
        ArrayList<String> ImageUrlList = new ArrayList<>();
        ArrayList<String> fileUrlList = new ArrayList<>();

        //Placedata has a field CityDetailsObject that has details of that place.
        CityDetail cityDetailObject;
        String siteMapUrl;
        for (PlaceData placeData : placesList) {

            if (!TextUtils.isEmpty(placeData.getImageurl()))
                ImageUrlList.add(placeData.getImageurl());

            cityDetailObject = placeData.getCityDetailObject();
            siteMapUrl = cityDetailObject.getSitemap();

            if (!TextUtils.isEmpty(cityDetailObject.getAudioimage()))
                ImageUrlList.add(cityDetailObject.getAudioimage());

            if (!TextUtils.isEmpty(siteMapUrl))
                ImageUrlList.add(siteMapUrl);

            if (!TextUtils.isEmpty(cityDetailObject.getShortdescription())) {
                fileUrlList.add(cityDetailObject.getShortdescription());
            }
        }
        if (ImageUrlList.size() > 0)
            SaveImageOnExternalStorageService.startService(ImageUrlList, getActivity());

        if (fileUrlList.size() > 0) {
            DownloadTextService.startToDownloadText(getActivity(), fileUrlList);
        }
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_places;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent, int resourceID, LayoutInflater inflater) {
        final Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_places_to_visit, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        final PlaceData placeData = placesList.get(position);

        if (placeData != null) {
            if (holder.tvPlaceName != null) {
                holder.tvPlaceName.setText(placeData.getName());
            }

            final String imageurl = placeData.getImageurl();


            if (!TextUtils.isEmpty(imageurl)) {
                PicassoUtil.loadImage(getActivity(), imageurl, holder.ivPlaceImage, R.mipmap.placeholder);
            } else {
                holder.ivPlaceImage.setImageResource(R.mipmap.placeholder);
            }
        }

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PlaceData placeData = placesList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.BUNDLE_KEYS.PLACE_DATA, placeData);
        startNextActivity(bundle, CityDetailActivity.class);
    }


    class Holder {

        ImageView ivPlaceImage;
        TextView tvPlaceName;

        public Holder(View view) {
            ivPlaceImage = (ImageView) view.findViewById(R.id.iv_place_image);
            tvPlaceName = (TextView) view.findViewById(R.id.tv_city_name);
        }
    }


}
