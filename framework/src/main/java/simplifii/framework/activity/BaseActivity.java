package simplifii.framework.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import simplifii.framework.R;
import simplifii.framework.exceptionhandler.RestException;
import simplifii.framework.fragments.TaskFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Util;
import simplifii.framework.widgets.CustomFontTextView;


public class BaseActivity extends AppCompatActivity implements
        OnClickListener, TaskFragment.AsyncTaskListener {

    protected ActionBar bar;
    protected View actionBarView;
    ProgressDialog dialog;
    protected boolean isCartIconNeeded = false;
    private TaskFragment taskFragment;
    protected Toolbar toolbar;

    protected final String TAG = getTag();

    public static boolean isInternetDialogVisible = false;

    public String getActionTitle() {
        return getResources().getString(R.string.app_name);
    }

    protected String getTag() {
        return this.getClass().getSimpleName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        // initActionBar();
        taskFragment = new TaskFragment();
        getSupportFragmentManager().beginTransaction().add(taskFragment, "task").commit();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
    }


    protected void initWindow() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.white);
    }

    public void initToolBarwithIcon(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getHomeIcon());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//       toolbar.setBackgroundColor(colorCode);

        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.color_title));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void onActionItemClicked(View v) {
        Log.i(TAG, "onActionItemClicked");
        switch (v.getId()) {

            default:
                break;
        }

    }

    protected void showingBannerAds(int id) {
        AdView adView = (AdView) findViewById(id);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            // do nothing
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setFullScreenWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams attrs = this.getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        this.getWindow().setAttributes(attrs);
    }


    public void onBackIconClicked(View v) {
        super.onBackPressed();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public AsyncTask executeTask(int taskCode, Object... params) {
        if (Util.isConnectingToInternet(this)) {
            taskFragment.createAsyncTaskManagerObject(taskCode)
                    .executeOnExecutor(TaskFragment.AsyncManager.THREAD_POOL_EXECUTOR, params);
        } else {
            Log.i("Base Activity", "Not Connected to internet");
//            showToast("Internet not connected...");
//            if (isInternetDialogVisible) {
//                Util.createAlertDialog(this, "Please Connect to Internet",
//                        "Not Connected to internet", false, "OK", "Cancel",
//                        internetDialogListener).show();
//                isInternetDialogVisible = true;
//            }
            onInternetException(taskCode);
        }
        return null;
    }


    protected void onInternetException(int taskCode) {
//        findViewById(R.id.frame_noInternet).setVisibility(View.VISIBLE);
    }

    public boolean isNetworkAvailable() {
        if (Util.isConnectingToInternet(this)) {
            return true;
        } else {
            Log.i("Base Activity", "Not Connected to internet");
            if (isInternetDialogVisible) {
                Util.createAlertDialog(this, "Please Connect to Internet",
                        "Not Connected to internet", false, "OK", "Cancel",
                        internetDialogListener).show();
                isInternetDialogVisible = true;
            }
            return false;
        }
    }

    public static Util.DialogListener internetDialogListener = new Util.DialogListener() {

        @Override
        public void onOKPressed(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
            isInternetDialogVisible = false;
        }

        @Override
        public void onCancelPressed(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
            isInternetDialogVisible = false;
        }
    };


    public void hideProgressBar() {
        Log.i(TAG + "Dialog", Thread.currentThread().getName());
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void showProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
        } else {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading...");
            dialog.show();
            dialog.setCancelable(false);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    public void setText(String text, int textViewId) {
        TextView view = (TextView) findViewById(textViewId);
        view.setText(text);
    }

    protected String getTextView(int textViewId) {
        return ((TextView) findViewById(textViewId)).getText().toString().trim();
    }


    public void setText(String text, int textViewId, View v) {
        TextView view = (TextView) v.findViewById(textViewId);
        view.setText(text);
    }


    private int cartCount = 0;


    public void startNextActivity(Class<? extends Activity> activityClass) {
        Intent i = new Intent(this, activityClass);
        startActivity(i);
    }

    public void startNextActivity(Bundle bundle,
                                  Class<? extends Activity> activityClass) {

        Intent i = new Intent(this, activityClass);
        if (null != bundle) {
            i.putExtras(bundle);
        }
        startActivity(i);
    }

    public void startActivityForResult(Class<? extends BaseActivity> activityClass, int requestCode, Bundle bundle) {
        Intent i = new Intent(this, activityClass);
        if (null != bundle) {
            i.putExtras(bundle);
        }
        startActivityForResult(i, requestCode);
    }


    protected void setOnClickListener(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setOnClickListener(this);
        }
    }

    protected String getEditText(int editTextId) {
        return ((EditText) findViewById(editTextId)).getText().toString()
                .trim();
    }

    @Override
    public void onPreExecute(int taskCode) {

        showProgressDialog();
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        hideProgressBar();
    }

    @Override
    public void onBackgroundError(RestException re, Exception e, int taskCode, Object... params) {
        hideProgressBar();
    }

    public void initToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getHomeIcon());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.color_title));
    }

    protected int getHomeIcon() {
        return R.mipmap.wireframes_final_44;
    }


    public void onRetryClicked(View view, int taskCode) {
        if (Util.isConnectingToInternet(this)) {
            findViewById(R.id.frame_noInternet).setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onHomePressed();
                return true;

        }
        return false;
    }

    protected void onHomePressed() {
        onBackPressed();
    }

    protected void onServerError() {
//        FrameLayout errorLayout = (FrameLayout) findViewById(R.id.frame_noInternet);
//        if (errorLayout != null) {
//            errorLayout.setVisibility(View.VISIBLE);
//            ImageView errorImage = (ImageView) errorLayout.findViewById(R.id.iv_error);
//            TextView errorMsg = (TextView) errorLayout.findViewById(R.id.tv_errorMsg);
//            TextView errorInfo = (TextView) errorLayout.findViewById(R.id.tv_errorInfo);
//
//            errorImage.setImageResource(R.drawable.icon_server_error);
//            errorMsg.setText("SERVER ERROR");
//            errorInfo.setText("Oops! Something went wrong...");
//        }
    }

    protected void hideVisibility(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setVisibility(View.GONE);
        }
    }

    protected void showVisibility(int... viewIds) {
        for (int i : viewIds) {
            findViewById(i).setVisibility(View.VISIBLE);
        }
    }

    protected int getResourceColor(int colorId) {
        return ContextCompat.getColor(this, colorId);
    }

    protected void changeFontOfTextView(int id, String font) {
        ((CustomFontTextView) findViewById(id)).setCustomFont(this, font);
    }

    protected void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void setError(int editTextId, String error) {
        EditText editText = (EditText) findViewById(editTextId);
        editText.setError(error);
    }

    private ArrayList<Integer> editTextList;
    private ArrayList<String> validationTypeList;
    private ArrayList<String> errorMessegeList;
    private ArrayList<String> fieldNames;

    protected void initialiseValidation() {
        editTextList = new ArrayList();
        validationTypeList = new ArrayList();
        errorMessegeList = new ArrayList();
        fieldNames = new ArrayList();
    }

    protected void setValidation(int editTextId, String validationType, String errorMessege) {
        editTextList.add(editTextId);
        validationTypeList.add(validationType);
        errorMessegeList.add(errorMessege);
        fieldNames.add("");
    }

    protected void setEmptyValidation(int editTextId, String fieldName) {
        editTextList.add(editTextId);
        validationTypeList.add(AppConstants.VALIDATIONS.EMPTY);
        errorMessegeList.add("");
        fieldNames.add(fieldName);
    }

    protected void setEmailValidation(int editTextId) {
        setEmptyValidation(editTextId, "Email");
        setValidation(editTextId, AppConstants.VALIDATIONS.EMAIL, "");
    }

    protected void setPhoneNumberValidation(int editTextId, String fieldName) {
        setEmptyValidation(editTextId, fieldName);
        setValidation(editTextId, AppConstants.VALIDATIONS.MOBILE, "");
    }

    protected boolean isValidate() {
        for (int x = 0; x < editTextList.size(); x++) {
            int id = editTextList.get(x);
            String validationType = validationTypeList.get(x);
            String errorMessage = errorMessegeList.get(x);

            EditText editText = (EditText) findViewById(id);

            switch (validationType) {
                case AppConstants.VALIDATIONS.EMPTY:
                    String text = editText.getText().toString();
                    if (TextUtils.isEmpty(text)) {
                        if ("".equals(errorMessage)) {
                            editText.setError(fieldNames.get(x) + " cannot be empty..!");
                        } else {
                            editText.setError(errorMessage);
                        }
                        return false;
                    }
                    break;
                case AppConstants.VALIDATIONS.EMAIL:
                    String email = editText.getText().toString();
                    if (!Util.isValidEmail(email)) {
                        if ("".equals(errorMessage))
                            editText.setError(getString(R.string.error_invalid_email));
                        else
                            editText.setError(errorMessage);
                        return false;
                    }
                    break;
                case AppConstants.VALIDATIONS.MOBILE:
                    String mobile = editText.getText().toString();
                    if (10 != mobile.length()) {
                        if ("".equals(errorMessage))
                            editText.setError(getString(R.string.error_invalid_mobile));
                        else
                            editText.setError(errorMessage);
                        return false;
                    }

                    break;
            }
        }
        return true;
    }

}
