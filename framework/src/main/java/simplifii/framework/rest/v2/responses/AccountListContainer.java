package simplifii.framework.rest.v2.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robin on 12/29/16.
 */

public class AccountListContainer {

    @SerializedName("data")
    @Expose
    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
