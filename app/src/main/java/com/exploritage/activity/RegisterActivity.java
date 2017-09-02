package com.exploritage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.exploritage.R;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.CollectionsUtils;
import simplifii.framework.utility.Preferences;

/**
 * Created by kartikeya on 25/7/17.
 */

public class RegisterActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setOnClickListener(R.id.button_sign_up);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_sign_up:
                onSignUp();
                break;
        }
    }

    private void onSignUp() {
        if (CollectionsUtils.isNotEmpty(getEditText(R.id.et_name))){
            if (CollectionsUtils.isNotEmpty(getEditText(R.id.et_phone_number)) && getEditText(R.id.et_phone_number).length()==10){
                if (CollectionsUtils.isNotEmpty(getEditText(R.id.et_password))){
                    if (CollectionsUtils.isNotEmpty(getEditText(R.id.et_confirm_password)) && getEditText(R.id.et_password).equals(getEditText(R.id.et_confirm_password))){
                        Preferences.saveData(Preferences.LOGIN_KEY,true);

                        startNextActivity(null,MainActivity.class);
                        finish();

                    }else{
                        showToast(getString(R.string.error_password_match));
                    }
                }else{
                    showToast(getString(R.string.error_empty_password));
                }
            }
            else{
                showToast(getString(R.string.error_invalid_phone_number));
            }
        }
        else{
            showToast(getString(R.string.error_empty_name));
        }
    }
}
