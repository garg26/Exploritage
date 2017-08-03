package com.exploritage.model.responses;

import android.text.TextUtils;

import com.exploritage.model.BaseApiResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;


/**
 * Created by ajay on 20/2/17.
 */

public class CityGuidesResponse extends BaseApiResponse {


    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public static CityGuidesResponse getInstance(){
        String json = Preferences.getData(AppConstants.PREF_KEYS.KEY_CITY_GUIDE_JSON, null);
        if(!TextUtils.isEmpty(json)){
            return parseJson(json);
        }
        return null;
    }

    public static CityGuidesResponse parseJson(String json){
        CityGuidesResponse cityGuidesResponse = (CityGuidesResponse) JsonUtil.parseJson(json, CityGuidesResponse.class);
        if(cityGuidesResponse != null){
            Preferences.saveData(AppConstants.PREF_KEYS.KEY_CITY_GUIDE_JSON, json);
        }
        return cityGuidesResponse;
    }
}
