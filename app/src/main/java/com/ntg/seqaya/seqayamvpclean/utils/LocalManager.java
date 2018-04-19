package com.ntg.seqaya.seqayamvpclean.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Locale;



public class LocalManager {
   public static Context onAttach(Context context ){
       String lang = getPersistedData(context , Locale.getDefault().getLanguage());
       return setLocale(context , lang);
   }
    public static Context onAttach(Context context , String defaultLanguage ){
        String lang = getPersistedData(context , defaultLanguage);
        return setLocale(context , lang);
    }

    public static Context setLocale(Context context , String lang){
        persist(context , lang);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return updateResources(context , lang);
        }

        return updateResourceLegacy(context , lang);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourceLegacy(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = context.getResources().getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLayoutDirection(locale);
        }
        resources.updateConfiguration(configuration , resources.getDisplayMetrics());
        return context;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        Log.i("lang" , lang);
        return context.createConfigurationContext(configuration);
    }

    private static void persist(Context context , String lang){
        SharedPreferences sharedPreferences = context.getSharedPreferences("lang", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", lang);
        editor.apply();
    }
    private static String getPersistedData(Context context , String language){
        SharedPreferences sharedPref = context.getSharedPreferences("lang",0);
        return sharedPref.getString("lang" , language);
    }
}
