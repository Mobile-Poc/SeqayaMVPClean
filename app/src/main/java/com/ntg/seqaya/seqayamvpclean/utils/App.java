package com.ntg.seqaya.seqayamvpclean.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.widget.LinearSmoothScroller;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class App extends Application {

    public static App context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static App getContext() {
        return context;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalManager.onAttach(base , "ar"));
    }
}
