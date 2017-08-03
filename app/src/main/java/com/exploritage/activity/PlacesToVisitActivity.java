package com.exploritage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.model.responses.Datum;
import com.exploritage.model.responses.place.PlaceData;
import com.exploritage.model.responses.place.PlaceToVisitResponse;
import com.exploritage.util.ApiGenerator;
import com.exploritage.util.PicassoUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 21/2/17.
 */

public class PlacesToVisitActivity extends BaseActivity implements CustomListAdapterInterface, AdapterView.OnItemClickListener {

    private CustomListAdapter adapter;
    private ArrayList<PlaceData> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placesto_visit);

        placesList = new ArrayList();
        ListView listView = (ListView) findViewById(R.id.lv_place_to_visit);
        adapter = new CustomListAdapter(this, R.layout.row_places_to_visit, placesList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Datum city = (Datum) extras.get(AppConstants.BUNDLE_KEYS.CITY_DATA);
            if (city != null) {
                String cityname = city.getCityname();
                initToolBar(cityname);
                getPlaceToVisitApi(city);
            }
        }


    }

    private void getPlaceToVisitApi(Datum city) {
        String objectId = city.getObjectId();
        if (!TextUtils.isEmpty(objectId)) {
            HttpParamObject httpParamObject = ApiGenerator.getPlace(objectId);
            executeTask(AppConstants.TASK_CODES.PLACES_TO_VISIT, httpParamObject);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PlaceData placeData = placesList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.BUNDLE_KEYS.PLACE_DATA, placeData);
        startNextActivity(bundle, CityDetailActivity.class);
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

                if (placeToVisitResponse != null) {

                    List<PlaceData> dataList = placeToVisitResponse.getData();
                    placesList.addAll(dataList);
                    adapter.notifyDataSetChanged();
                }
                break;

        }

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

        if (holder.tvPlaceName != null) {
            holder.tvPlaceName.setText(placeData.getName());
        }

        final String imageurl = placeData.getImageurl();


        if (!TextUtils.isEmpty(imageurl)) {
            PicassoUtil.loadImage(PlacesToVisitActivity.this, imageurl
                    , holder.ivPlaceImage, R.mipmap.placeholder);
        } else {
            holder.ivPlaceImage.setImageResource(R.mipmap.placeholder);
        }

        return convertView;
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
