package com.exploritage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.model.DownloadDestinationInformation;
import com.exploritage.sqlite.CityDetaildatabase;
import com.exploritage.util.PicassoUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import simplifii.framework.ListAdapters.CustomListAdapter;
import simplifii.framework.ListAdapters.CustomListAdapterInterface;
import simplifii.framework.activity.BaseActivity;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.Preferences;


public class MyAudioDestinationActivity extends BaseActivity implements CustomListAdapterInterface {
    private ArrayList<DownloadDestinationInformation> audioCityInformations = new ArrayList<>();
    private CustomListAdapter adapter;
    private List<DownloadDestinationInformation> cityInformationList = new ArrayList<>();
    private CityDetaildatabase cityDetaildatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_city_destination);
        cityDetaildatabase = new CityDetaildatabase(this);

//        TextView tv_title_name = (TextView) findViewById(R.id.tv_title_name);
//        tv_title_name.setText("My Destinations");

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String cityID = bundle.getString(AppConstants.BUNDLE_KEYS.CITY_DETAIL);
            if (!TextUtils.isEmpty(cityID)){
                List<DownloadDestinationInformation> audioCityInformationList = cityDetaildatabase.getDetail(cityID);
                if (audioCityInformationList!=null) {
                    for (DownloadDestinationInformation cityInformation : audioCityInformationList) {
                        cityInformationList.add(cityInformation);
                    }
                }
            }
        }



        ListView listView = (ListView) findViewById(R.id.lv_available_city_guides);
        if (audioCityInformations != null) {
            adapter = new CustomListAdapter(this, R.layout.fragment_available_city_guides, cityInformationList, this);
            listView.setAdapter(adapter);
        } else {
            listView.setVisibility(View.GONE);
        }
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

        final DownloadDestinationInformation cityInformation = cityInformationList.get(position);
        holder.tvCityName.setText(cityInformation.getDestinationName());
        final String image_url = cityInformation.getDestinationImageUrl();
        if (!TextUtils.isEmpty(image_url)) {
            PicassoUtil.loadImage(this, image_url, holder.ivCityImage, R.mipmap.placeholder);
        } else {
            holder.ivCityImage.setImageResource(R.mipmap.placeholder);
        }

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityDetaildatabase.delete(cityInformation.getObjectID());
                DownloadDestinationInformation detail = cityDetaildatabase.getDetailByDestinationID(cityInformation.getObjectID());
                deleteAudioFile(cityInformation.getAudioFilePath());

                if (detail==null){
                    showToast(getString(R.string.delete_destination_successfully));
                    cityInformationList.remove(cityInformation);
                    adapter.notifyDataSetChanged();
                }
                adapter.notifyDataSetChanged();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return convertView;
    }

    private void deleteAudioFile(String audioFilePath) {
        if (!TextUtils.isEmpty(audioFilePath)){
            File file = new File(audioFilePath);
            if (file!=null){
                file.delete();
            }

        }
    }

    class Holder {

        ImageView ivCityImage,iv_delete;
        TextView tvCityName;

        public Holder(View view) {
            ivCityImage = (ImageView) view.findViewById(R.id.iv_city_image);
            tvCityName = (TextView) view.findViewById(R.id.tv_city_name);
            iv_delete = (ImageView)view.findViewById(R.id.iv_delete);

        }
    }
}
