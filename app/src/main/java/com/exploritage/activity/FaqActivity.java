package com.exploritage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.exploritage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simplifii.framework.activity.BaseActivity;

/**
 * Created by Prashant Goyal on 17-07-2017.
 */

public class FaqActivity extends BaseActivity {

    //for faq list view
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    Map<String, String> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        initToolBarwithIcon("");
        TextView tv_toolbar = (TextView) findViewById(R.id.tv_title_name_faq);
        tv_toolbar.setText("FAQ's");
        setExpandableList();
        setOnClickListener(R.id.iv_back_faq);
    }


    private void setExpandableList() {
        // get listview
        expListView = (ExpandableListView) findViewById(R.id.expand_list_view);

        //preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        //set list adapter
        expListView.setAdapter(listAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_faq:
                onBackPressed();
                break;
        }
    }



    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, String>();

        //add child data
        listDataHeader.add("Frequently Asked Question 1?");
        listDataHeader.add("Frequently Asked Question 2?");
        listDataHeader.add("Frequently Asked Question 3?");
        listDataHeader.add("Frequently Asked Question 4?");


        //add Child Data
        String faq1 = "Test String for Faq 1 ";
        String faq2 = "Test String for faq 2";
        String faq3 = "Test String for FAQ 3";
        String faq4 = "Test String for FAQ 4";

        listDataChild.put(listDataHeader.get(0), faq1);
        listDataChild.put(listDataHeader.get(1), faq2);
        listDataChild.put(listDataHeader.get(2), faq3);
        listDataChild.put(listDataHeader.get(3), faq4);


    }

    @Override
    protected void onHomePressed() {

    }


}
