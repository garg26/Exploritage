package simplifii.framework.rest.v2.responses;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import simplifii.framework.rest.v2.responses.AccountListContainer;
import simplifii.framework.rest.v2.responses.BaseApiResponse;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by robin on 12/29/16.
 */

public class GetAccountApiResponse extends BaseApiResponse {

    @SerializedName("response")
    @Expose
    private AccountListContainer accountListContainer;

    public AccountListContainer getAccountListContainer() {
        return accountListContainer;
    }

    public void setAccountListContainer(AccountListContainer accountListContainer) {
        this.accountListContainer = accountListContainer;
    }

    public static GetAccountApiResponse getInstance() {
        String json = Preferences.getData(AppConstants.PREF_KEYS.KEY_ACCOUNT_LIST_JSON, "");
        if (!TextUtils.isEmpty(json)) {
            return parseJson(json);
        }
        return null;
    }

    public static GetAccountApiResponse parseJson(String json) {
        GetAccountApiResponse response = (GetAccountApiResponse) JsonUtil.parseJson(json, GetEmployeesApiResponse.class);
        if (response != null) {
            Preferences.saveData(AppConstants.PREF_KEYS.KEY_ACCOUNT_LIST_JSON, json);
        }
        return response;
    }
}
