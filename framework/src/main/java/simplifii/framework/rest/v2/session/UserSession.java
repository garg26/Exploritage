package simplifii.framework.rest.v2.session;

import android.text.TextUtils;

import simplifii.framework.rest.v2.responses.UserProfile;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.JsonUtil;
import simplifii.framework.utility.Preferences;

/**
 * Created by robin on 12/20/16.
 */

public class UserSession extends UserProfile {

    private String token;
    private boolean loggedIn;

    public UserSession(String token, boolean loggedIn, UserProfile userProfile) {
        super(userProfile);
        this.token = token;
        this.loggedIn = loggedIn;
    }

    private static UserSession sessionInstance;

    public static UserSession getSessionInstance() {
        if(sessionInstance==null){
            String json = Preferences.getData(AppConstants.PREF_KEYS.USER_SESSION, "");
            if(!TextUtils.isEmpty(json)){
                sessionInstance = (UserSession) JsonUtil.parseJson(json,UserSession.class);
                sessionInstance.loggedIn = Preferences.getData(Preferences.LOGIN_KEY, false);
                sessionInstance.token = Preferences.getData(Preferences.KEY_AUTH_TOKEN, "");
            }
        }
        return sessionInstance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public static void saveUserSessionAndLogIn(String token, UserProfile userProfile) {
        sessionInstance = new UserSession(token, true, userProfile);
        String json = JsonUtil.toJson(userProfile);
        Preferences.saveData(Preferences.USER_SESSION,json);
        Preferences.saveData(Preferences.LOGIN_KEY, true);
        Preferences.saveData(Preferences.KEY_AUTH_TOKEN, token);
    }

    public static void removeCurrentSession() {
        sessionInstance = null;
        Preferences.removeData(Preferences.USER_SESSION);
        Preferences.removeData(Preferences.LOGIN_KEY);
        Preferences.removeData(Preferences.KEY_AUTH_TOKEN);
    }
}
