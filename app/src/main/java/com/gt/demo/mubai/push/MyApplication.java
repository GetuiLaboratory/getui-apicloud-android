package com.gt.demo.mubai.push;

import android.content.Context;
import android.util.Log;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.agconnect.config.LazyInputStream;
import com.uzmap.pkg.uzapp.UZApplication;

import java.io.IOException;
import java.io.InputStream;

public class MyApplication extends UZApplication {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        AGConnectServicesConfig config = AGConnectServicesConfig.fromContext(context);
        config.overlayWith(new LazyInputStream(context) {
            public InputStream get(Context context) {
                try {
                    Log.d("Assist_", "-------->huawei------read config");
                    return context.getAssets().open("agconnect-services.json");
                } catch (IOException e) {
                    return null;
                }
            }
        });

    }
}
