package com.android.haozi.wanandroid;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

public class WanAndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
