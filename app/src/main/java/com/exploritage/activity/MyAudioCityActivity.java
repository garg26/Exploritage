package com.exploritage.activity;

import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.model.DownloadCityInformation;
import com.exploritage.model.DownloadDestinationInformation;
import com.exploritage.sqlite.CityDetaildatabase;
import com.exploritage.sqlite.DatabaseHandler;
import com.exploritage.util.PicassoUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionsUtils;


/**
 * Created by kartikeya on 27/7/17.
 */

public class MyAudioCityActivity extends BaseActivity implements CustomListAdapterInterface {

    private List<DownloadCityInformation> cityInformations = new ArrayList<>();
    private CustomListAdapter adapter;
    private ArrayList<DownloadDestinationInformation> audioCityInformations = new ArrayList<>();
    private DatabaseHandler databaseHandler;
    private CityDetaildatabase cityDetaildatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_city_destination);

        databaseHandler = new DatabaseHandler(this);
        cityDetaildatabase = new CityDetaildatabase(this);
//        TextView tv_title_name = (TextView) findViewById(R.id.tv_title_name);
//        tv_title_name.setText("My Destinations");

        showingBannerAds(R.id.adView);
        setdata();

    }

    private void setdata() {
        List<DownloadCityInformation> allDetails = databaseHandler.getAllDetails();
        cityInformations.addAll(allDetails);

        ListView listView = (ListView) findViewById(R.id.lv_available_city_guides);
        if (audioCityInformations != null) {
            adapter = new CustomListAdapter(this, R.layout.fragment_available_city_guides, cityInformations, this);
            listView.setAdapter(adapter);
        } else {
            listView.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getHomeIcon() {
        return R.mipmap.wireframes_final_40;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent, int resourceID, LayoutInflater inflater) {
        final Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_audio_destination_detail, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final DownloadCityInformation cityInformation = cityInformations.get(position);
        if (!TextUtils.isEmpty(cityInformation.getCityName())) {
            holder.tvCityName.setText(cityInformation.getCityName());
        }
        final String image_url = cityInformation.getCityImageUrl();
        if (!TextUtils.isEmpty(image_url)) {
            PicassoUtil.loadImage(this, image_url, holder.ivCityImage, R.mipmap.placeholder);
        } else {
            holder.ivCityImage.setImageResource(R.mipmap.placeholder);
        }

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandler.delete(cityInformation.getCityID());
                deleteDestination(cityDetaildatabase.getDetail(cityInformation.getCityID()));
                List<DownloadCityInformation> detail = databaseHandler.getDetail(cityInformation.getCityID());
                if (detail == null || detail.size() == 0) {
                    showToast(getString(R.string.delete_city_successfully));
                    cityInformations.remove(cityInformation);
                    adapter.notifyDataSetChanged();
                }

            }


        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.BUNDLE_KEYS.CITY_DETAIL, cityInformation.getCityID());
                startNextActivity(bundle, MyAudioDestinationActivity.class);
            }
        });
        return convertView;
    }

    private void deleteDestination(List<DownloadDestinationInformation> detail) {
        if (CollectionsUtils.isNotEmpty(detail)) {
            for (DownloadDestinationInformation destinationInformation : detail) {
                String audioFilePath = destinationInformation.getAudioFilePath();
                if (!TextUtils.isEmpty(audioFilePath)) {
                    File file = new File(audioFilePath);
                    if (file!=null) {
                        file.delete(); // Delete the audio file path
                    }
                }
            }
        }
    }


    class Holder {

        ImageView ivCityImage, iv_delete;
        TextView tvCityName;

        public Holder(View view) {
            ivCityImage = (ImageView) view.findViewById(R.id.iv_city_image);
            tvCityName = (TextView) view.findViewById(R.id.tv_city_name);
            iv_delete = (ImageView) view.findViewById(R.id.iv_delete);

        }
    }
}
