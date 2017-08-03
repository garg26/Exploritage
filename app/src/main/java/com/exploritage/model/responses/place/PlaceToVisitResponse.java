package com.exploritage.model.responses.place;

import android.text.TextUtils;

import com.exploritage.model.BaseApiResponse;
import com.exploritage.model.responses.CityDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by ajay on 21/2/17.
 */

public class PlaceToVisitResponse extends BaseApiResponse {

    @SerializedName("data")
    @Expose
    private List<PlaceData> data = null;

    public List<PlaceData> getData() {
        return data;
    }

    public void setData(List<PlaceData> data) {
        this.data = data;
    }




    public static PlaceToVisitResponse getInstance(String key){
        String json = Preferences.getData(AppConstants.PREF_KEYS.KEY_PLACE_TO_VISIT_JSON+key, null);
        if(!TextUtils.isEmpty(json)){
            return parseJsonData(json);
        }
        return null;
    }

    public static PlaceToVisitResponse parseJsonData(String json){
        PlaceToVisitResponse cityGuidesResponse = (PlaceToVisitResponse) JsonUtil.parseJson(json, PlaceToVisitResponse.class);
        return cityGuidesResponse;
    }
}
