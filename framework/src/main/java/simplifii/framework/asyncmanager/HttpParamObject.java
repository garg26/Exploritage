package simplifii.framework.asyncmanager;


import android.provider.SyncStateContract;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import simplifii.framework.Network.ClientURLConnection;
import simplifii.framework.rest.responses.BaseResponse;
import simplifii.framework.rest.v2.responses.BaseApiResponse;
import simplifii.framework.rest.v2.responses.OtpContainer;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;
import simplifii.framework.utility.Prefs;

import java.lang.reflect.Type;
import java.util.HashMap;

public class HttpParamObject {

    private String url = "";
    private HashMap<String, String> postParams = new HashMap<>();
    private HashMap<String, String> headers = new HashMap<String, String>();
    private Class classType = BaseApiResponse.class;
    private String method = ClientURLConnection.GET_METHOD;
    private String json = "";
    private String contentType = "application/json";
    private String authToken;
    private int taskCode;

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(int taskCode) {
        this.taskCode = taskCode;
    }

    public HttpParamObject() {
    }

    public void addAuthTokenHeader(String authToken) {
        this.authToken = authToken;
        addHeader("Authorization", "bearer " + authToken);
    }

    public void addAuthTokenHeader() {
        this.authToken = Preferences.getData(Preferences.KEY_AUTH_TOKEN, "");
        Log.d("AuthToken", authToken);
        addHeader("Authorization", "bearer " + authToken);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMethod() {
        return method;
    }

    public void setPutMethod() {
        this.method = ClientURLConnection.PUT_METHOD;
    }

    public void setPostMethod() {
        this.method = ClientURLConnection.POST_METHOD;
    }


    public void setPatchMethod() {
        this.method = ClientURLConnection.PATCH_METHOD;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getPostParams() {
        return postParams;
    }

    public void setPostParams(HashMap<String, String> postParams) {
        this.postParams = postParams;
    }

    public Class getClassType() {
        return classType;
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }

    public void addParameter(String name, String value) {
        postParams.put(name, value);
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }


    public void setJSONContentType() {
        contentType = "application/json";
    }

    public void addJsonContentTypeHeader() {
        contentType = "application/json";
        addHeader("Content-Type", contentType);
    }

    public<T> void setApiBasedClassType(Class<T> classType) {
//        Type type = new TypeToken<BaseApiResponse<T>>(){}.getType();
//        this.classType = type.getClass();
    }
}
