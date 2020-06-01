package com.tianyu.login_mvp_extract.app;

import android.app.Application;

public class MvpApplication extends Application {
    private static MvpApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static MvpApplication getApp() {
        return app;
    }
}
