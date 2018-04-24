package com.ntg.seqaya.seqayamvpclean.data.datasource.local.sharedprefernce;

import android.content.Context;
import android.content.SharedPreferences;

import com.ntg.seqaya.seqayamvpclean.utils.App;

/**
 * Created by islam on 4/22/2018.
 */

public class SharedPrferenceClient {
    private static SharedPreferences  sharedPreferences= null;



    public static SharedPreferences getSharedPreferencesClient(String key){
        if (sharedPreferences == null){
            sharedPreferences = App.getContext().getSharedPreferences(key , 0);
        }
        return sharedPreferences;
    }
}
