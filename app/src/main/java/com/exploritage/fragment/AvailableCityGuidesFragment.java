package com.exploritage.fragment;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.activity.SiteDescriptionActivity;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.model.responses.CityGuidesResponse;
import com.exploritage.model.responses.Datum;
import com.exploritage.service.DownloadTextService;
import com.exploritage.service.SaveImageOnExternalStorageService;
import com.exploritage.util.ApiGenerator;
import com.exploritage.util.HomeFragmentListener;
import com.exploritage.util.PicassoUtil;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 18/2/17.
 */

public class AvailableCityGuidesFragment extends BaseFragment implements CustomListAdapterInterface, AdapterView.OnItemClickListener {

    private CustomListAdapter adapter;
    private List<Datum> availableCityList;
    private HomeFragmentListener homeFragmentListener;
    private ArrayList<CityDetail> cityDetailList;
    private TextView tvNoCityFound;
    private ProgressBar progressBar;




    public static Fragment getInstance(HomeFragmentListener homeFragmentListener) {
        AvailableCityGuidesFragment availableCityGuidesFragment = new AvailableCityGuidesFragment();
        availableCityGuidesFragment.homeFragmentListener = homeFragmentListener;
        return availableCityGuidesFragment;
    }

    @Override
    public void initViews() {
        progressBar = (ProgressBar) findView(R.id.progressbar);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.color_light_green), PorterDuff.Mode.MULTIPLY);
        tvNoCityFound = (TextView) findView(R.id.tv_empty_list);
        cityDetailList = new ArrayList<>();

        //    homeFragmentListener.setTitle(getString(R.string.available_city_guides));
        availableCityList = new ArrayList<>();
        ListView lvAvailableCityGuides = (ListView) findView(R.id.lv_available_city_guides);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        View view = inflater.inflate(R.layout.listview_header, null);
//        lvAvailableCityGuides.addHeaderView(view);

        CityGuidesResponse cityGuidesResponse = CityGuidesResponse.getInstance();
        if (cityGuidesResponse != null && cityGuidesResponse.getData() != null) {
            availableCityList.clear();
            availableCityList.addAll(cityGuidesResponse.getData());
        }


        adapter = new CustomListAdapter(getActivity(), R.layout.row_available_city_guides, availableCityList, this);
        lvAvailableCityGuides.setAdapter(adapter);
        lvAvailableCityGuides.setOnItemClickListener(this);
//        lvAvailableCityGuides.setEmptyView(tvNoCityFound);
        getAvailableCityGuidesApi();
    }

    private void getAvailableCityGuidesApi() {
        HttpParamObject httpParamObject = ApiGenerator.getCityGuides();
        executeTask(AppConstants.TASK_CODES.AVAILABLE_CITY_GUIDES, httpParamObject);
    }

    @Override
    public void onResume() {
        super.onResume();
        //homeFragmentListener.setTitle(getString(R.string.welcome_to_exploritage));
        //homeFragmentListener.setSubTitle(getString(R.string.heritage_personal_guide));
    }


    @Override
    public void showProgressBar() {
//        super.showProgressBar();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
//        super.hideProgressBar();
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
            case AppConstants.TASK_CODES.AVAILABLE_CITY_GUIDES:
                CityGuidesResponse cityGuidesResponse = (CityGuidesResponse) response;
                if (cityGuidesResponse != null) {
                    availableCityList.clear();
                    List<Datum> data = cityGuidesResponse.getData();
                    availableCityList.addAll(data);
                    saveImagesLocally();

                    if (data.size() == 0) {
                        tvNoCityFound.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void checkPermission() {
        new TedPermission(getActivity())
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        ArrayList<String> imageUrlList = new ArrayList<>();
                        ArrayList<String> fileUrlList = new ArrayList<>();
//        ArrayList<String> mapUrlList= new ArrayList<>();
                        String imageUrl, mapUrl;
                        for (Datum cityData : availableCityList) {
                            imageUrl = cityData.getImage_url();
                            mapUrl = cityData.getSitemap();
                            if (!TextUtils.isEmpty(imageUrl))
                                imageUrlList.add(imageUrl);
                            if (!TextUtils.isEmpty(mapUrl))
                                imageUrlList.add(mapUrl);
                            if (!TextUtils.isEmpty(cityData.getDescription())) {
                                fileUrlList.add(cityData.getDescription());
                            }
                        }
                        if (imageUrlList.size() > 0)
                            SaveImageOnExternalStorageService.startService(imageUrlList, getActivity());
                        if (fileUrlList.size() > 0) {
                            DownloadTextService.startToDownloadText(getActivity(), fileUrlList);
                        }
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                        showToast("Permission to write to storage denied");
                    }
                }).check();
    }

    private void saveImagesLocally() {
        checkPermission();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openSubsite(position);
    }

    private void openSubsite(int position) {
        if (cityDetailList != null) {
            Datum city = availableCityList.get(position);
            if (city != null) {
                if (city.isHasData()) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(AppConstants.BUNDLE_KEYS.CITY_DATA, city);
                    startNextActivity(bundle, SiteDescriptionActivity.class);
                } else {
//                   showToast("Coming Soon");
                    openDialog();
                }
            }
        }
    }



    private void openDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_package_name);
        dialog.show();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent, int resourceID, LayoutInflater inflater) {
        final Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_available_city_guides, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Datum datum = availableCityList.get(position);
        holder.tvCityName.setText(datum.getCityname());
        final String image_url = datum.getImage_url();
        if (!TextUtils.isEmpty(image_url)) {
            PicassoUtil.loadImage(getActivity(), image_url, holder.ivCityImage, R.mipmap.placeholder);
        } else {
            holder.ivCityImage.setImageResource(R.mipmap.placeholder);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(null,view,position,0);
            }
        });
        return convertView;
    }




    class Holder {

        ImageView ivCityImage;
        TextView tvCityName;

        public Holder(View view) {
            ivCityImage = (ImageView) view.findViewById(R.id.iv_city_image);
            tvCityName = (TextView) view.findViewById(R.id.tv_city_name);
        }
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_available_city_guides;
    }
}
