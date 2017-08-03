package com.exploritage.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.exploritage.util.MultipleTextFilesLoader;
import com.exploritage.util.ReadFromUrlUtil;

import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadTextService extends Service {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String DOWNLOAD_TEXT_FILE = "com.exploritage.service.action.downloadtextfile";
    private static final String ACTION_BAZ = "com.exploritage.service.action.BAZ";

    // TODO: Rename parameters
    private static final String FILE_NAME = "com.exploritage.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.exploritage.service.extra.PARAM2";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startToDownloadText(Context context, ArrayList<String> fileName) {
        Intent intent = new Intent(context, DownloadTextService.class);
        intent.setAction(DOWNLOAD_TEXT_FILE);
        intent.putExtra(FILE_NAME, fileName);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadTextService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(FILE_NAME, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final String action = intent.getAction();
            if (DOWNLOAD_TEXT_FILE.equals(action)) {
                final ArrayList<String> param1 = (ArrayList<String>) intent.getSerializableExtra(FILE_NAME);
                startDownloadingText(param1);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(FILE_NAME);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     *
     * @param param1
     */
    private void startDownloadingText(ArrayList<String> param1) {
        // TODO: Handle action Foo
        new MultipleTextFilesLoader(param1).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
