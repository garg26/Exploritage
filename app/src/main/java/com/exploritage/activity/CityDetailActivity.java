package com.exploritage.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.fragment.CityFragment;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.model.responses.CityDetailsResponse;
import com.exploritage.model.responses.Datum;
import com.exploritage.model.responses.place.PlaceData;
import com.exploritage.util.ApiGenerator;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;
import simplifii.framework.utility.Util;

/**
 * Created by ajay on 21/2/17.
 */

public class CityDetailActivity extends BaseActivity {
    PlaceData placeData;
    private ArrayList<CityDetail> cityDetailList;
    private ProgressBar progressBar;
    private TextView tvTitle;
    private Datum city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);
        initToolBarwithIcon("");

        TextView iv_title_name = (TextView) findViewById(R.id.tv_title_name);
        cityDetailList = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            placeData=(PlaceData) bundle.getSerializable(AppConstants.BUNDLE_KEYS.PLACE_DATA);
            city = (Datum) bundle.getSerializable(AppConstants.BUNDLE_KEYS.CITY_DETAIL);
            CityDetail cityDetailHolder = placeData.getCityDetailObject();
            String placeName = placeData.getName();
            if (!TextUtils.isEmpty(placeName)) {
                iv_title_name.setText(placeName);

            }
            addCityFragment();
        }
        showingBannerAds(R.id.adView);
        setOnClickListener(R.id.iv_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    //    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        getCityDetailsAPI(placeData);
//    }


    private void addCityFragment() {
        Fragment fragment = CityFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.BUNDLE_KEYS.CITY_DETAIL,city);
        bundle.putSerializable(AppConstants.BUNDLE_KEYS.PLACE_DATA, placeData);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.lay_fragment_container, fragment).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_destination, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    private void getCityDetailsAPI(PlaceData placeData) {
        String objectId = placeData.getObjectId();
        if (!TextUtils.isEmpty(objectId)) {
            if (Util.isConnectingToInternet(this)) {
                HttpParamObject httpParamObject = ApiGenerator.getCityDetails(objectId);
                executeTask(AppConstants.TASK_CODES.CITY_DETAILS, httpParamObject);
            } else {
                CityDetailsResponse cityDetailsResponse = CityDetailsResponse.getInstance(objectId);
                if (cityDetailsResponse != null)
                    setCityDetailList(cityDetailsResponse);
            }
        }
    }

    private void savePlaceData(CityDetailsResponse cityDetailsResponse) {
        String objectId = placeData.getObjectId();
        if (!TextUtils.isEmpty(objectId)) {
            String json = JsonUtil.toJson(cityDetailsResponse);
            if (!TextUtils.isEmpty(json)) {
                Preferences.saveData("Place:"+objectId, json);
            }
        }
    }

    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPreExecute(int taskCode) {
        super.onPreExecute(taskCode);
        showProgressDialog();
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);

        hideProgressBar();

        switch (taskCode) {
            case AppConstants.TASK_CODES.CITY_DETAILS:
                CityDetailsResponse cityDetailsResponse = (CityDetailsResponse) response;
                savePlaceData(cityDetailsResponse);
                setCityDetailList(cityDetailsResponse);
//                saveImagesLocally();
                break;
        }
    }

    @Override
    protected void onHomePressed() {

    }

    //    private void saveImagesLocally(){
//        ArrayList<String> imageUrlList=new ArrayList<>();
//        ArrayList<String> mapUrlList=new ArrayList<>();
//        for(CityDetail cityDetail:cityDetailList){
//            String imageUrl = cityDetail.getAudioimage();
//            String mapUrl = cityDetail.getSitemap();
//            if(!TextUtils.isEmpty(imageUrl))
//                imageUrlList.add(imageUrl);
//            if(!TextUtils.isEmpty(mapUrl))
//                mapUrlList.add(mapUrl);
//        }
//        if(imageUrlList.size()>0)
//            SaveImageOnExternalStorageService.startService(imageUrlList,CityDetailActivity.this);
//        if(mapUrlList.size()>0)
//            SaveImageOnExternalStorageService.startService(mapUrlList, CityDetailActivity.this);
//    }

    private void setCityDetailList(CityDetailsResponse cityDetailsResponse) {
        List<CityDetail> data = cityDetailsResponse.getData();
        if (data != null && data.size() > 0) {
            if (!TextUtils.isEmpty(placeData.getDownloadedFilePath()))
                data.get(0).setDownloadedFilePath(placeData.getDownloadedFilePath());
            data.get(0).setDownloadedFlag(placeData.getDownloadedFlag());
            cityDetailList.addAll(data);
            addCityFragment();
        }
    }
}
