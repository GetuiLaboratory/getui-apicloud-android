package com.getui.sdk;

import com.igexin.sdk.PushService;

/**
 * 核心服务, 继承 android.app.Service, 必须实现以下几个接口, 并在 AndroidManifest 声明该服务并配置成
 * android:process=":pushservice"
 * PushManager.getInstance().initialize(this.getApplicationContext(), userPushService), 其中
 * userPushService 为 用户自定义服务 即 DemoPushService.
 */
public class APICloudPushService extends PushService {

    private static final String TAG = "APICloudPushService";


//    @Override
//    public void onCreate() {
//        Log.d(TAG, TAG + " call -> onCreate -------");
//
//        super.onCreate();
//        GTServiceManager.getInstance().onCreate(this);
//    }
//
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(TAG, TAG + " call -> onStartCommand -------");
//
//        super.onStartCommand(intent, flags, startId);
//        return GTServiceManager.getInstance().onStartCommand(this, intent, flags, startId);
//    }
//
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.d(TAG, "onBind -------");
//        return GTServiceManager.getInstance().onBind(intent);
//    }
//
//
//    @Override
//    public void onDestroy() {
//        Log.d(TAG, "onDestroy -------");
//
//        super.onDestroy();
//        GTServiceManager.getInstance().onDestroy();
//    }
//
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        GTServiceManager.getInstance().onLowMemory();
//    }


}
