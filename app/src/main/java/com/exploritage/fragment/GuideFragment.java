package com.exploritage.fragment;

import android.Manifest;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.exploritage.R;
import com.exploritage.util.AppBaseFragment;
import com.exploritage.util.DownloadFileUtil;
import com.exploritage.util.PicassoUtil;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import hybridmediaplayer.HybridMediaPLayer;
import simplifii.framework.utility.AppConstants;
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
    private RelativeLayout layDownload;
    private String audioImageUrl;
    private String audioFileUrl;
    Handler seekHandler = new Handler();
    String filename = "";

    public static Fragment getInstance(String audioFileUrl, String audioImageUrl) {
        GuideFragment guideFragment = new GuideFragment();
        guideFragment.audioFileUrl = audioFileUrl;
        guideFragment.audioImageUrl = audioImageUrl;
        return guideFragment;
    }

    @Override
    public void initViews() {
        layDownload = (RelativeLayout) findView(R.id.lay_download);
        tvDownload = (TextView) findView(R.id.tv_downloading);
        progressBar = (ProgressBar) findView(R.id.progressbar);
        ivSubsite = (ImageView) findView(R.id.iv_subsite);
        lay_Play_Pause = (RelativeLayout) findView(R.id.lay_play_control);
        viewContainer = (LinearLayout) findView(R.id.lay_view_container);
        String title = getResources().getString(R.string.title_guide_des);
        ivDownloadAudio = (ImageView) findView(R.id.iv_download);
        ivPlay_Pause = (ImageView) findView(R.id.iv_play_pause);
        iv_Pause = (ImageView) findView(R.id.iv_pause);
        tvStartTime = (TextView) findView(R.id.tv_start_time);
        tvCompleteTime = (TextView) findView(R.id.tv_end_time);
        seekBar = (SeekBar) findView(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);
        mediaPlayer = HybridMediaPLayer.getInstance(getActivity());
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);

        mediaPlayer.setOnPreparedListener(this);
        setOnClickListener(R.id.lay_download, R.id.lay_play_control);
        setAudioPlayerBackground();
        setPlayerDataSource();
//        setGuideDescription(title);
    }

    private void setAudioPlayerBackground() {
        if (!TextUtils.isEmpty(audioImageUrl)) {
            PicassoUtil.loadImage(getActivity(), audioImageUrl, ivSubsite, 0);
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
            if (audioFileUrl != null) {
                if (isAudioFilePresentOnExternalStorage()) {
                    String path = Preferences.getData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + audioFileUrl, "");
                    isFileDownloaded = true;
//                  showToast("from Local");
                    ivDownloadAudio.setImageResource(R.mipmap.ic_successful);
                    mediaPlayer.setDataSource(new File(path).toString());
                    mediaPlayer.prepare();
                } else {
                    isFileDownloaded = false;
//                    showToast("from Server");
                    mediaPlayer.setDataSource(audioFileUrl);
                    mediaPlayer.prepare();
                }
                setDownloadAttrs();
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }


    }

    private boolean isAudioFilePresentOnExternalStorage() {
        String path = Preferences.getData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + audioFileUrl, "");
        if (!TextUtils.isEmpty(path)) {
            File audioFile = new File(path);
            if (audioFile.exists())
                return true;
            else
                return false;
        } else
            return false;
    }

    private void setDownloadAttrs() {
        if (isFileDownloaded) {
            ivDownloadAudio.setImageResource(R.mipmap.ic_successful);
            tvDownload.setText("Downloaded");
        } else {
            ivDownloadAudio.setImageResource(R.mipmap.ic_download);
            tvDownload.setText("Download");
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.lay_play_control:
                playAudio();
                break;
            case R.id.lay_subsite_image_container:
                playAudio();
                break;
            case R.id.lay_download:
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
            tvDownload.setText("Downloading...");
            ivDownloadAudio.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

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
                tvDownload.setText("Downloaded");
                progressBar.setVisibility(View.GONE);
                ivDownloadAudio.setImageResource(R.mipmap.ic_successful);
                ivDownloadAudio.setVisibility(View.VISIBLE);

                String filePath = result;
                if (!TextUtils.isEmpty(audioFileUrl))
                    Preferences.saveData(AppConstants.PREF_KEYS.KEY_AUDIO_FILE + audioFileUrl, filePath);
            } else {
            }
        }


    }


    private void playAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            iv_Pause.setVisibility(View.INVISIBLE);
            ivPlay_Pause.setVisibility(View.VISIBLE);
        } else {
            if (Util.isConnectingToInternet(getActivity()) || isFileDownloaded) {
                ivPlay_Pause.setVisibility(View.INVISIBLE);
                iv_Pause.setVisibility(View.VISIBLE);
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
