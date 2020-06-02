package com.mo.zhou.timer;

import android.app.Application;

import com.example.skin_library.SkinManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
    }
}
