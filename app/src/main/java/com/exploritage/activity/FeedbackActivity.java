package com.exploritage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.model.responses.feedback.FeedbackApiResponse;

import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;

/**
 * Created by ajay on 22/2/17.
 */

public class FeedbackActivity extends BaseActivity {

   // private Button btnFeedback;
   // private EditText etWriteFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_new);
        initToolBarwithIcon("");
        TextView tv = (TextView) findViewById(R.id.tv_title_name);
        tv.setText("FeedBack");
        setTitle("Feedback");
        showingBannerAds(R.id.adView);
       // btnFeedback = (Button) findViewById(R.id.btn_post_feedback);
       // etWriteFeedback = (EditText) findViewById(R.id.et_feedback);
        setOnClickListener(R.id.iv_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }


    @Override
    protected void onHomePressed() {

    }


//    @Override
//    public void onClick(View v) {
//        super.onClick(v);
//
//        switch (v.getId()) {
//            case R.id.btn_post_feedback:
//                postFeedbackApi();
//                break;
//        }
//    }

//    private void postFeedbackApi() {
//
//        String feedback = etWriteFeedback.getText().toString();
//        if (!TextUtils.isEmpty(feedback)) {
//            HttpParamObject httpParamObject = ApiGenerator.postFeedbackData(feedback);
//            executeTask(AppConstants.TASK_CODES.POST_FEEDBACK, httpParamObject);
//        } else {
//            showToast("write something");
//            return;
//        }
//    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);

        if (response == null) {
            showToast(getResources().getString(R.string.no_response));
            return;
        }

        if (taskCode == AppConstants.TASK_CODES.POST_FEEDBACK) {

            FeedbackApiResponse feedbackApiResponse = (FeedbackApiResponse) response;
            showToast("Thank you for your Feedback");
            //etWriteFeedback.setText("");
        }

    }
}
