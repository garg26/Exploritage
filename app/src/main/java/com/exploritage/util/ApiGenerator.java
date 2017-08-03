package com.exploritage.util;

import com.exploritage.model.responses.CityDetailsResponse;
import com.exploritage.model.responses.CityGuidesResponse;
import com.exploritage.model.responses.feedback.FeedbackApiResponse;
import com.exploritage.model.responses.place.PlaceToVisitResponse;
import com.exploritage.model.responses.sharetext.ShareTextResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 20/2/17.
 */

public class ApiGenerator {


    public static HttpParamObject getCityGuides() {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.setUrl(AppConstants.PAGE_URL.CITY_GUIDE);
        httpParamObject.setJSONContentType();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("application-id", "7B1F2DAD-9A9F-00DD-FF80-B391C0149500");
        headers.put("secret-key", "F797F74E-863A-AB3C-FF07-2B5B5B509500");
        httpParamObject.setHeaders(headers);
        httpParamObject.addParameter("sortBy", "order asc");
        httpParamObject.addParameter("pageSize", "100");
        httpParamObject.setClassType(CityGuidesResponse.class);
        return httpParamObject;
    }

    public static HttpParamObject getHttpObjectForShareText(){
        HttpParamObject httpParamObject = new HttpParamObject();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("application-id", "7B1F2DAD-9A9F-00DD-FF80-B391C0149500");
        headers.put("secret-key", "F797F74E-863A-AB3C-FF07-2B5B5B509500");
        httpParamObject.setHeaders(headers);
        httpParamObject.setJSONContentType();
        httpParamObject.setUrl(AppConstants.PAGE_URL.SHARE_TEXT_URL);
        httpParamObject.setClassType(ShareTextResponse.class);




        return  httpParamObject;

    }

    public static HttpParamObject getCityDetails(String objectId) {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.setUrl(AppConstants.PAGE_URL.CITY_DETAILS);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("application-id", "7B1F2DAD-9A9F-00DD-FF80-B391C0149500");
        headers.put("secret-key", "F797F74E-863A-AB3C-FF07-2B5B5B509500");
        httpParamObject.setHeaders(headers);
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("where", "placeid='" + objectId + "'");
        httpParamObject.setPostParams(postParams);
        httpParamObject.setClassType(CityDetailsResponse.class);

        return httpParamObject;
    }

    public static HttpParamObject getPlace(String objectId) {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.setUrl(AppConstants.PAGE_URL.PLACE_TO_VISIT);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("application-id", "7B1F2DAD-9A9F-00DD-FF80-B391C0149500");
        headers.put("secret-key", "F797F74E-863A-AB3C-FF07-2B5B5B509500");
        httpParamObject.setHeaders(headers);
        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("sortBy", "order asc");
        postParams.put("where", "cityid='" + objectId + "'");
        postParams.put("pageSize", "100");
        httpParamObject.setPostParams(postParams);
        httpParamObject.setClassType(PlaceToVisitResponse.class);
        return httpParamObject;
    }

    public static HttpParamObject postFeedbackData(String feedback) {
        HttpParamObject httpParamObject = new HttpParamObject();
        httpParamObject.setUrl(AppConstants.PAGE_URL.POST_FEEDBACK);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("application-id", "7B1F2DAD-9A9F-00DD-FF80-B391C0149500");
        headers.put("secret-key", "F797F74E-863A-AB3C-FF07-2B5B5B509500");
        httpParamObject.setHeaders(headers);
        httpParamObject.setPostMethod();
        httpParamObject.setClassType(FeedbackApiResponse.class);
//        httpParamObject.setContentType();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("feedback", feedback);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        httpParamObject.setJson(jsonObject.toString());

        return httpParamObject;

    }
}
