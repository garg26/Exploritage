package com.exploritage.fragment;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.model.DownloadDestinationInformation;
import com.exploritage.model.DownloadCityInformation;
import com.exploritage.model.responses.CityDetail;
import com.exploritage.model.responses.Datum;
import com.exploritage.sqlite.CityDetaildatabase;
import com.exploritage.sqlite.DatabaseHandler;
import com.exploritage.util.AppBaseFragment;
import com.exploritage.util.DownloadFileUtil;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import hybridmediaplayer.HybridMediaPLayer;
import simplifii.framework.utility.AppConstants;
import simplifii.framework.utility.CollectionsUtils;
import simplifii.framework.utility.Preferences;
import simplifii.framework.utility.Util;

/**
 * Created by ajay on 18/2/17.
 */
public class GuideFragment extends AppBaseFragment implements HybridMediaPLayer.OnCompletionListener, HybridMediaPLayer.OnErrorListener, HybridMediaPLayer.OnPreparedListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {
    private LinearLayout viewContainer;
    private ImageView ivDownloadAudio;
    private RelativeLayout lay_Play_Pause;
    private HybridMediaPLayer mediaPlayer;
    private ImageView ivPlay_Pause, ivSubsite, iv_Pause;
    private SeekBar seekBar;
    private TextView tvStartTime, tvCompleteTime, tvDownload;
    private double startTime, completeTime;
    private ProgressBar progressBar;
    private boolean isFileDownloaded = false;
    //private RelativeLayout layDownload;
    private String audioImageUrl;
    private String audioFileUrl;
    Handler seekHandler = new Handler();
    String filename = "";
    private DownloadDestinationInformation audioCityInformation;
    private DatabaseHandler databaseHandler;
    private Datum city;
    private CityDetail cityDetail;
    private CityDetaildatabase cityDetaildatabase;
    private List<String> audioUrlList = new ArrayList<>();

    public static Fragment getInstance(String audioFileUrl, String audioImageUrl, Datum city, CityDetail cityDetail) {
        GuideFragment guideFragment = new GuideFragment();
        guideFragment.audioFileUrl = audioFileUrl;
        guideFragment.audioImageUrl = audioImageUrl;
        guideFragment.city = city;
        guideFragment.cityDetail = cityDetail;
        return guideFragment;
    }

    @Override
    public void initViews() {
        //layDownload = (RelativeLayout) findView(R.id.lay_download);

        databaseHandler = new DatabaseHandler(getActivity());
        cityDetaildatabase = new CityDetaildatabase(getActivity());
        tvDownload = (TextView) findView(R.id.tv_downloading);
        progressBar = (ProgressBar) findView(R.id.progressbar);
        ivSubsite = (ImageView) findView(R.id.iv_subsite);
        //lay_Play_Pause = (RelativeLayout) findView(R.id.lay_play_control);
        viewContainer = (LinearLayout) findView(R.id.lay_view_container);
        String title = getResources().getString(R.string.title_guide_des);
        ivDownloadAudio = (ImageView) findView(R.id.iv_download);
        ivPlay_Pause = (ImageView) findView(R.id.iv_play_pause);
        //  iv_Pause = (ImageView) findView(R.id.iv_pause);
        tvStartTime = (TextView) findView(R.id.tv_start_time);
        tvCompleteTime = (TextView) findView(R.id.tv_end_time);
        seekBar = (SeekBar) findView(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);
        mediaPlayer = HybridMediaPLayer.getInstance(getActivity());
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);

        mediaPlayer.setOnPreparedListener(this);
        setOnClickListener(R.id.iv_download, R.id.iv_play_pause);
        setAudioPlayerBackground();
        setPlayerDataSource();
//        setGuideDescription(title);
    }

    private void setAudioPlayerBackground() {
        if (!TextUtils.isEmpty(audioImageUrl)) {
            Picasso.with(getActivity()).load(audioImageUrl).into(ivSubsite);
        } else {
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    private void setPlayerDataSource() {
        try {
            if (!TextUtils.isEmpty(audioFileUrl)) {
                String destinationID = cityDetail.getObjectId();
                if (!TextUtils.isEmpty(destinationID)) {
                    DownloadDestinationInformation detailByDestinationID = cityDetaildatabase.getDetailByDestinationID(destinationID);
                    if (detailByDestinationID != null) {
                        String audioFilePath = detailByDestinationID.getAudioFilePath();
                        File file = new File(audioFilePath);
                        if (!TextUtils.isEmpty(audioFilePath) && file.exists()) {
                            isFileDownloaded = true;
                            ivDownloadAudio.setImageResource(R.mipmap.ic_successful);
                            mediaPlayer.setDataSource(new File(audioFilePath).toString());
                            mediaPlayer.prepare();
                        } else {
                            isFileDownloaded = false;
                            mediaPlayer.setDataSource(audioFileUrl);
                            mediaPlayer.prepare();
                        }
                    } else {
                        isFileDownloaded = false;
                        mediaPlayer.setDataSource(audioFileUrl);
                        mediaPlayer.prepare();
                    }
                }
            }
//                if (isAudioFilePresentOnExternalStorage()) {
//                    String path = Preferences.getData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + audioFileUrl, "");
//                    isFileDownloaded = true;
////                  showToast("from Local");
//                    ivDownloadAudio.setImageResource(R.mipmap.ic_successful);
//                    mediaPlayer.setDataSource(new File(path).toString());
//                    mediaPlayer.prepare();
//                }
//                else {
//                    isFileDownloaded = false;
////                    showToast("from Server");
//                    mediaPlayer.setDataSource(audioFileUrl);
//                    mediaPlayer.prepare();
//                }
            setDownloadAttrs();

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }


    }

//    private boolean isAudioFilePresentOnExternalStorage() {
//        String path = Preferences.getData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + audioFileUrl, "");
//        if (!TextUtils.isEmpty(path)) {
//            File audioFile = new File(path);
//            if (audioFile.exists())
//                return true;
//            else
//                return false;
//        } else
//            return false;
//    }

    private void setDownloadAttrs() {
        if (isFileDownloaded) {
            ivDownloadAudio.setImageResource(R.mipmap.ic_successful);
            //tvDownload.setText("Downloaded");
        } else {
            ivDownloadAudio.setImageResource(R.mipmap.ic_download);
            //tvDownload.setText("Download");
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_play_pause:
                playAudio();
                break;
//            case R.id.iv_pause:
//                playAudio();
//                break;
            case R.id.iv_download:
                // showAlterDialog();
                if (isFileDownloaded == false) {
                    if (Util.isConnectingToInternet(getActivity()))
                        downloadAudioFile();
                    else
                        showToast("No internet connection available");


                } else {
                    showToast("Audio file is already downloaded");
                }

                break;
        }
    }


    private void showAlterDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_want_to_download);
        dialog.show();
    }

    private void downloadAudioFile() {
        saveFileToStorage();
    }

    private void saveFileToStorage() {
        new TedPermission(getActivity())
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        if (!TextUtils.isEmpty(audioFileUrl))
                            new DownloadAudioFileTask().execute(audioFileUrl);


                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                }).check();
    }

    private class DownloadAudioFileTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            tvDownload.setVisibility(View.VISIBLE);
//            tvDownload.setText("Downloading...");
            ivDownloadAudio.setVisibility(View.GONE);
//            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {

            String url = params[0];
//            String filePath = downloadFile(url);
            String filePath = DownloadFileUtil.downloadAudioFile(url, "filename");
            return filePath;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (!TextUtils.isEmpty(result)) {
                isFileDownloaded = true;
                //tvDownload.setText("Downloaded");
                //progressBar.setVisibility(View.GONE);
                ivDownloadAudio.setImageResource(R.mipmap.ic_successful);
                ivDownloadAudio.setVisibility(View.VISIBLE);

                String filePath = result;
//                if (!TextUtils.isEmpty(audioFileUrl) && city != null)
//                    Preferences.saveData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + audioFileUrl, filePath);
                String cityname = city.getCityname();
                String cityID = city.getObjectId();
                String image_url = city.getImage_url();
                if (!TextUtils.isEmpty(cityID) && !TextUtils.isEmpty(cityID) && !TextUtils.isEmpty(image_url)) {
                    DownloadCityInformation cityInformation = new DownloadCityInformation(cityID, cityname, image_url);
                    databaseHandler.insert(cityInformation);
                }
                String objectId = cityDetail.getObjectId();
                String destinationName = cityDetail.getName();
                String destination_imageUrl = cityDetail.getAudioimage();
                if (!TextUtils.isEmpty(objectId) && !TextUtils.isEmpty(destinationName) && !TextUtils.isEmpty(destination_imageUrl) && !TextUtils.isEmpty(cityID) && !TextUtils.isEmpty(filePath)) {
                    DownloadDestinationInformation audioCityInformation = new DownloadDestinationInformation(cityID, objectId, destinationName, destination_imageUrl, filePath);
                    cityDetaildatabase.insert(audioCityInformation);
                }

            }

        }


    }


    private void playAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            ivPlay_Pause.setImageResource(R.mipmap.play_circle_white);
            // iv_Pause.setVisibility(View.INVISIBLE);
            // ivPlay_Pause.setVisibility(View.VISIBLE);
        } else {
            if (Util.isConnectingToInternet(getActivity()) || isFileDownloaded) {
                ivPlay_Pause.setImageResource(R.mipmap.pause_button);
                //ivPlay_Pause.setVisibility(View.INVISIBLE);
                //iv_Pause.setVisibility(View.VISIBLE);
                startPlayer();
            } else
                showToast("No internet connectivity available");
        }
    }

    private void startPlayer() {
        mediaPlayer.play();
        startTime = mediaPlayer.getCurrentPosition();
        completeTime = mediaPlayer.getDuration();
        startPlayProgressUpdater();
    }

    private void startPlayProgressUpdater() {
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    startPlayProgressUpdater();
                }
            };
            seekHandler.postDelayed(runnable, 500);
            tvStartTime.setText(toMinuteSecond(mediaPlayer.getCurrentPosition()));
            tvCompleteTime.setText(toMinuteSecond(mediaPlayer.getDuration()));
        } else {
            mediaPlayer.pause();
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
        }
    }

    private String toMinuteSecond(double time) {

        String minute_second = String.format("%02d : %02d", TimeUnit.MILLISECONDS.toMinutes((long) time), TimeUnit.MILLISECONDS.toSeconds((long) time) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) time)));
        return minute_second;
    }

    @Override
    public void onCompletion(HybridMediaPLayer hybridMediaPLayer) {
        ivPlay_Pause.setImageResource(R.mipmap.ic_play);
        seekBar.setProgress(0);
    }

    @Override
    public void onError(HybridMediaPLayer hybridMediaPLayer) {
//        showToast("Error occurred...");
    }

    @Override
    public void onPrepared(HybridMediaPLayer hybridMediaPLayer) {
//        showToast("onPreapared");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        seekChange(v);
        return false;
    }

    private void seekChange(View view) {
        SeekBar sb = (SeekBar) view;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(sb.getProgress());
        } else {
            mediaPlayer.seekTo(sb.getProgress());
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mediaPlayer.seekTo(progress);
            seekBar.setProgress(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public int getViewID() {
        return R.layout.fragment_guide;
    }

}
