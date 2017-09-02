package com.exploritage.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.exploritage.R;
import com.exploritage.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import org.json.JSONObject;

import java.util.Arrays;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.Logger;
import simplifii.framework.utility.Preferences;


/**
 * Created by kartikeya on 25/7/17.
 */

public class FireBaseFBLoginUtil {

    private static final String TAG = "FireBaseFBLoginUtil";
    private CallbackManager callbackManager;
    private Activity ctx;
    private FBLoginCallback callback;
    private ProgressDialog dialog;

    public FireBaseFBLoginUtil(Activity ctx, FBLoginCallback callback) {
        this.ctx = ctx;
        this.callback = callback;
        onCreate();
    }

    private void onCreate() {

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Logger.info(TAG, "onSuccess" + loginResult.getAccessToken().toString());
                loginResult.getAccessToken().toString();
                requestUserFBData(loginResult);
                Preferences.saveData("access_token", loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                Logger.info(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
                Logger.info(TAG, "onError");
            }
        });
    }

    public void initiateFbLogin() {

        LoginManager.getInstance().logInWithReadPermissions(ctx, Arrays.asList("public_profile", "email"));
//        accessToken = AccessToken.getCurrentAccessToken();
    }

    private void requestUserFBData(final LoginResult loginResult) {

        handleFacebookAccessToken(loginResult.getAccessToken(),ctx);
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Logger.info(TAG, response.toString());
                        // Get facebook data from login
                        final Bundle bFacebookData = getFacebookData(object);
                        final JSONObject jsonObject = new JSONObject();

//                        {
//                            "oauthProvider": "facebook",
//                                "accessToken": "ASDasdasdasdasdas",
//                                "profileId": "1231232312312"
//                        }

                        try {
                            bFacebookData.putString("oauthProvider", "facebook");
                            bFacebookData.putString("accessToken", loginResult.getAccessToken().getToken());
                            bFacebookData.putString("profileId", bFacebookData.getString("idFacebook", ""));
                            ctx.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onSuccess(bFacebookData);
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                            ctx.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onFailure();
                                }
                            });
                        }


                    }
                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
        request.setParameters(parameters);
        request.executeAsync();
    }
    private void handleFacebookAccessToken(AccessToken token, Activity activity) {

        Log.d(TAG, "handleFacebookAccessToken:" + token);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        showProgressDialog();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressBar();
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {

                        }

                        // ...
                    }
                });
    }
    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");


            bundle.putString("idFacebook", id);
            String firstName = "", lastName = "";
            if (object.has("first_name")) {
                Preferences.saveData("first_name", object.getString("first_name"));
                bundle.putString("name", object.getString("first_name"));
                firstName = object.getString("first_name");
            }
            if (object.has("last_name")) {
                bundle.putString("last_name", object.getString("last_name"));
                Preferences.saveData("last_name", object.getString("last_name"));
                lastName = object.getString("last_name");
            }

            Preferences.saveData("name", firstName + " " + lastName);

            if (object.has("email")) {
                bundle.putString("email", object.getString("email"));
                Preferences.saveData("email", object.getString("email"));
            }
            if (object.has("gender")) {
                bundle.putString("gender", object.getString("gender"));
                String gender = object.getString("gender");
            }
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        } catch (Exception e) {

        }
        return null;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public interface FBLoginCallback {
        void onSuccess(Bundle bundle);
        void onFailure();
    }
    public void showProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
        } else {
            dialog = new ProgressDialog(ctx);
            dialog.setMessage("Loading...");
            dialog.show();
            dialog.setCancelable(false);
        }
    }
    public void hideProgressBar() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
