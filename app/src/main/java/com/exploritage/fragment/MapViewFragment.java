package com.exploritage.fragment;

import android.view.View;
import android.widget.TextView;
import com.exploritage.R;
import com.exploritage.model.responses.place.PlaceData;
import com.exploritage.model.responses.place.PlaceToVisitResponse;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import simplifii.framework.fragments.BaseFragment;
import simplifii.framework.utility.CollectionsUtils;

/**
 * Created by kartikeya on 25/7/17.
 */

public class MapViewFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;
    private List<LatLng> lat_lon_list = new ArrayList<>();
    private List<String> city_destination_list = new ArrayList<>();

    private HashMap<LatLng, String> stringHashMap;
    private PlaceToVisitResponse placeToVisitResponse;
    private final List<Marker> mMarkerRainbow = new ArrayList<Marker>();


    public static MapViewFragment getInstance(PlaceToVisitResponse placeToVisitResponse) {
        MapViewFragment mapViewFragment = new MapViewFragment();
        mapViewFragment.placeToVisitResponse=placeToVisitResponse;
        return mapViewFragment;
    }

    @Override
    public void initViews() {
        loadBundle();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = mapFragment.getView();
        view.setClickable(false);
        view.setBackgroundResource(R.color.full_transparent);
        mapFragment.getMapAsync(this);


    }

    private void loadBundle() {

        stringHashMap = new HashMap<>();

        if (placeToVisitResponse != null) {
            List<PlaceData> placeDataList = placeToVisitResponse.getData();
            String destination_name = placeDataList.get(0).getName();
            stringHashMap.put(new LatLng(12.955488, 77.579462), destination_name);
            String destination_name1 = placeDataList.get(1).getName();
            stringHashMap.put(new LatLng(22.6321853, 88.3558912), destination_name1);


        }
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_map_view;
    }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;
        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(false);


        mMap.setInfoWindowAdapter(this);

        try {
        // Add lots of markers to the map.
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (stringHashMap != null) {
            for (Map.Entry<LatLng, String> stringEntry : stringHashMap.entrySet()) {
                LatLng latLngs = stringEntry.getKey();
                String destination = stringHashMap.get(latLngs);
                Marker marker =  mMap.addMarker(new MarkerOptions()
                        .position(latLngs)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                marker.setTag(destination);
                builder.include(latLngs).build();


            }
        }


            int width = getResources().getDisplayMetrics().widthPixels;
            int padding = (int) (width * 0.05); // offset from edges of the map in pixels
            if (builder.build() != null) {
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(builder.build(), padding);
                mMap.moveCamera(cu);
            }
        }catch (IllegalStateException e){
            showToast("Failed");
            e.printStackTrace();
        }


        mMap.setOnMarkerClickListener(this);


    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;

    }


    @Override
    public View getInfoWindow(Marker marker) {
        double angle = 0.0;
        double x = Math.sin(-angle * Math.PI / 180) * 0.5 + 0.5;
        double y = -(Math.cos(-angle * Math.PI / 180) * 0.5 - 0.5);
        View v = getActivity().getLayoutInflater().inflate(R.layout.view_on_click_map_marker, null);

        TextView tv_destination = (TextView) v.findViewById(R.id.tv_destination);
        String destination = marker.getTag().toString();
        if (CollectionsUtils.isNotEmpty(destination)){
            tv_destination.setText(destination);
        }
        marker.setInfoWindowAnchor((float)x, (float)y -0.2f); // set position of window anchor from marker.-0.1f for moving up
        return v;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

}
