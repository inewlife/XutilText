package com.inewlife.xutiltext.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;

import java.util.List;


/**
 * Created by inewlife on 2016/3/3.
 */
public class DownloadService extends Service {
    private static DownloadManager DOWNLOAD_MANAGER;

    public static DownloadManager getDownloadManager(Context appContext) {
        if (!DownloadService.isServiceRunning(appContext)) {
            Intent downloadSvr = new Intent("download.service.action");
            downloadSvr.setPackage("com.inewlife.xutiltext.service");
            appContext.startService(downloadSvr);
        }
        if (DownloadService.DOWNLOAD_MANAGER == null) {
            DownloadService.DOWNLOAD_MANAGER = new DownloadManager(appContext);
        }
        return DOWNLOAD_MANAGER;
    }

    private static boolean isServiceRunning(Context context) {
        boolean isRunning = false;

        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(Integer.MAX_VALUE);

        if (serviceList == null || serviceList.size() == 0) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(DownloadService.class.getName())) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }


    public DownloadService() {
        super();
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (DOWNLOAD_MANAGER != null) {
            try {
                DOWNLOAD_MANAGER.stopAllDownload();
                DOWNLOAD_MANAGER.backupDownloadInfoList();
            } catch (DbException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        super.onDestroy();
    }
}
