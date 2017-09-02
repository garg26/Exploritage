package com.exploritage.util;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.exploritage.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import simplifii.framework.utility.AppConstants;

/**
 * Created by kartikeya on 26/7/17.
 */

public class FireBaseGoogleLoginUtil implements GoogleApiClient.OnConnectionFailedListener{
    private static GoogleApiClient mGoogleApiClient;
    private AppCompatActivity activity;
    private FirebaseAuth mFirebaseAuth;
    private GoogleSignInListener googleSignInListener;
    private String TAG = "FCM";
    private ProgressDialog dialog;

    public static FireBaseGoogleLoginUtil getInstance(AppCompatActivity activity, GoogleSignInListener googleSignInListener) {
        FireBaseGoogleLoginUtil fireBaseGoogleLoginUtil = new FireBaseGoogleLoginUtil();
        fireBaseGoogleLoginUtil.activity = activity;
        fireBaseGoogleLoginUtil.googleSignInListener = googleSignInListener;
        fireBaseGoogleLoginUtil.init();
        return fireBaseGoogleLoginUtil;
    }

    private void init() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(activity, connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    public void login() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, AppConstants.REQUEST_CODES.GOOGLE_SIGHN_IN);
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGooogle:" + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        showProgressDialog();
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                hideProgressBar();
                Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                if (task.isSuccessful()) {
                    googleSignInListener.onSuccess(account);
                } else {
                    googleSignInListener.onFailed();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConstants.REQUEST_CODES.GOOGLE_SIGHN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                googleSignInListener.onFailed();
            }
        }
    }
    public interface GoogleSignInListener {
        void onSuccess(GoogleSignInAccount account);
        void onFailed();
    }
    public void showProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
        } else {
            dialog = new ProgressDialog(activity);
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
