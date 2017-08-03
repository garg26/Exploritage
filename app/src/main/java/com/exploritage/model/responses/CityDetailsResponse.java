package com.exploritage.model.responses;

import android.text.TextUtils;

import com.exploritage.model.BaseApiResponse;
import com.exploritage.model.responses.place.PlaceToVisitResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by ajay on 20/2/17.
 */

public class CityDetailsResponse extends BaseApiResponse {

    @SerializedName("data")
    @Expose
    private List<CityDetail> data = null;

    public List<CityDetail> getData() {
        return data;
    }

    public void setData(List<CityDetail> data) {
        this.data = data;
    }

    public static CityDetailsResponse getInstance(String cityId) {
        String json = Preferences.getData("Place:" + cityId, null);
        if (!TextUtils.isEmpty(json)) {
            return parseJsonData(json);
        }
        return null;
    }

    public static CityDetailsResponse parseJsonData(String json) {
        CityDetailsResponse cityGuidesResponse = (CityDetailsResponse) JsonUtil.parseJson(json, CityDetailsResponse.class);
        return cityGuidesResponse;
    }
}
