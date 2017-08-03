package com.exploritage.util;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exploritage.R;

import simplifii.framework.fragments.BaseFragment;

/**
 * Created by ajay on 19/2/17.
 */

public class AppBaseFragment extends BaseFragment {
    private boolean isExpended;
    public TextView tvTitle, tvDescription;
    public ImageView ivExpendLayout;

    @Override
    public void initViews() {

    }

    @Override
    public int getViewID() {
        return 0;
    }


    public View getDetailView(String title, String description, LinearLayout viewContainter, boolean expended) {
//        final TextView tvTitle, tvDescription;
//        final ImageView ivExpendLayout;
        isExpended = expended;
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.view_row_history, viewContainter, false);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvDescription = (TextView) view.findViewById(R.id.tv_description);
        ivExpendLayout = (ImageView) view.findViewById(R.id.iv_drop_down_arrow);
        tvTitle.setText(title);

//        setOnClickListener(R.id.tv_title, R.id.iv_drop_down_arrow);

        tvDescription.setText(description);
        if (isExpended) {
            expendView(tvDescription, ivExpendLayout);
        }

        tvTitle.setOnClickListener(this);
        ivExpendLayout.setOnClickListener(this);

       /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isExpended) {
                    collapseView(tvDescription, ivExpendLayout);
                } else {
                    expendView(tvDescription, ivExpendLayout);
                }
            }
        });
*/
        return view;
    }

    private void collapseView(TextView tvDescription, ImageView ivExpendLayout) {
        isExpended = false;
        tvDescription.setVisibility(View.GONE);
        ivExpendLayout.setImageResource(R.mipmap.ic_arrow_left);
    }

    private void expendView(TextView tvDescription, ImageView ivExpendLayout) {
        isExpended = true;
        tvDescription.setVisibility(View.VISIBLE);
        ivExpendLayout.setImageResource(R.mipmap.ic_arrow_down);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_title:
            case R.id.iv_drop_down_arrow:
                changeView();
                break;
        }

    }

    private void changeView() {
        if (isExpended) {
            collapseView(tvDescription, ivExpendLayout);
        } else {
            expendView(tvDescription, ivExpendLayout);
        }
    }
}
