package com.ntg.seqaya.seqayamvpclean.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        SharedPreferences sharedPref = this.getSharedPreferences("lang",0);
//        String language =  sharedPref.getString("lang" , "ar");
//        Locale locale = new Locale(language);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalManager.onAttach(base , "en"));
    }
}
