package com.mo.zhou.timer;

import android.app.Application;

import com.mo.zhou.commom.utils.Helper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Helper.mContext = this;
        //SkinManager.init(this);
    }
}
