package com.exploritage.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.model.responses.CityGuidesResponse;
import com.exploritage.model.responses.Datum;
import com.exploritage.util.ApiGenerator;
import com.exploritage.util.PicassoUtil;

import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.ListAdapters.CustomPagerAdapter;
import simplifii.framework.asyncmanager.HttpParamObject;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionsUtils;

/**
 * Created by kartikeya on 25/7/17.
 */

public class PackageFragment extends BaseFragment implements CustomListAdapterInterface {

    private List<Datum> availableCityList;
    private CustomPagerAdapter pagerAdapter;
    private GridView gridView;
    private Button btn_buy_now;
    private List<String> selected_city = new ArrayList<>();
    private RelativeLayout rl_btn_buy;


    @Override
    public void initViews() {
        availableCityList = new ArrayList<>();
        btn_buy_now = (Button) findView(R.id.btn_buy_now);
        gridView = (GridView) findView(R.id.gridview);


        setOnClickListener(R.id.btn_buy_now);
        getAvailableCityGuidesApi();

    }

    private void getAvailableCityGuidesApi() {
        HttpParamObject httpParamObject = ApiGenerator.getCityGuides();
        executeTask(AppConstants.TASK_CODES.AVAILABLE_CITY_GUIDES, httpParamObject);
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_package;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_buy_now:
                showAlterDialog();
                break;
        }
    }
    private void showAlterDialog() {
        if (selected_city.size()>0) {
            Dialog dialog = new Dialog(getActivity());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.activity_dialog_package_name);
            dialog.show();
        }else{
            showToast("Please Select the city");
        }
    }

    @Override
    public void onPostExecute(Object response, int taskCode, Object... params) {
        super.onPostExecute(response, taskCode, params);
        if (null == response) {
            showToast(getResources().getString(R.string.no_response));
            return;
        }
        switch (taskCode) {
            case AppConstants.TASK_CODES.AVAILABLE_CITY_GUIDES:
                CityGuidesResponse cityGuidesResponse = (CityGuidesResponse) response;
                if (cityGuidesResponse != null) {
                    availableCityList.clear();
                    List<Datum> data = cityGuidesResponse.getData();
                    availableCityList.addAll(data);

                }
                setAdapter(availableCityList);
                break;
        }
    }

    private void setAdapter(List<Datum> availableCityList) {
        CustomListAdapter listAdapter = new CustomListAdapter(getActivity(), R.layout.row_packages, availableCityList, this);
        gridView.setAdapter(listAdapter);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent, int resourceID, LayoutInflater inflater) {
        final Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_packages, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final Datum datum = availableCityList.get(position);
        final String image_url = datum.getImage_url();
        String cityname = datum.getCityname();
        if (!TextUtils.isEmpty(cityname)) {
            holder.ivCityName.setText(cityname);
        }
        if (!TextUtils.isEmpty(image_url)) {
            PicassoUtil.loadImage(getActivity(), image_url, holder.ivCityImage, R.mipmap.placeholder);
        } else {
            holder.ivCityImage.setImageResource(R.mipmap.placeholder);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = holder.cb_select.isChecked();
                String cityName = holder.ivCityName.getText().toString();
                if (checked) {
                    holder.cb_select.setChecked(false);

                    if (CollectionsUtils.isNotEmpty(cityName)) {
                        if (selected_city.contains(cityName)) {
                            selected_city.remove(cityName);
                        }
                    }


                } else {
                    if (CollectionsUtils.isNotEmpty(cityName)) {
                        if (!selected_city.contains(cityName)) {
                            selected_city.add(cityName);
                        }
                    }
                    holder.cb_select.setChecked(true);


                }

                if (selected_city.size() == 0) {
                    btn_buy_now.setBackgroundResource(R.mipmap.wireframes_final_37);

                } else {
                    btn_buy_now.setBackgroundResource(R.mipmap.wireframes_final_35);

                }


            }

        });


        if (selected_city.size() == 0) {
            btn_buy_now.setBackgroundResource(R.mipmap.wireframes_final_37);

        } else {
            btn_buy_now.setBackgroundResource(R.mipmap.wireframes_final_35);

        }

        return convertView;
    }


    class Holder {

        ImageView ivCityImage;
        TextView ivCityName;
        CheckBox cb_select;

        public Holder(View view) {
            ivCityImage = (ImageView) view.findViewById(R.id.iv_package);
            ivCityName = (TextView) view.findViewById(R.id.tv_city_name);
            cb_select = (CheckBox) view.findViewById(R.id.cb_select);

        }
    }


}
