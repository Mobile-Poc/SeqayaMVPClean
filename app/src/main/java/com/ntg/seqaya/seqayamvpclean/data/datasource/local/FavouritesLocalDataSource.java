package com.ntg.seqaya.seqayamvpclean.data.datasource.local;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ntg.seqaya.seqayamvpclean.data.datasource.FavouriteLocalDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.local.sharedprefernce.SharedPrferenceClient;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class FavouritesLocalDataSource implements FavouriteLocalDataSource {
    private static final String KEY = "FAVOURITE";
    private static FavouritesLocalDataSource INSTANCE;

    private FavouritesLocalDataSource() {
    }

    public static FavouritesLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FavouritesLocalDataSource();

        }
        return INSTANCE;
    }

    @Override
    public void addFav(List<Product> favouriteList, Product fav) {


        favouriteList.add(fav);
        Gson gson = new Gson();
        String favouritesString = gson.toJson(favouriteList);
        SharedPrferenceClient.getSharedPreferencesClient(KEY)
                .edit()
                .putString(KEY, favouritesString)
                .commit();

    }

    @Override
    public void deleteFav(List<Product> favouriteList, Product favouritesProduct ) {
        for (int i=0 ; i<favouriteList.size() ; i++){
            if (favouritesProduct.isLiked()){
                favouriteList.remove(i);
            }
        }
        Gson gson = new Gson();
        String favouritesString = gson.toJson(favouriteList);
        SharedPrferenceClient.getSharedPreferencesClient(KEY)
                .edit()
                .putString(KEY, favouritesString)
                .commit();
    }

    @Override
    public void getFavs(SuccessCallback<List<Product>> successCallback, ErrorCallback errorCall) {

        Gson gson = new Gson();
        String favouriteListString = SharedPrferenceClient.getSharedPreferencesClient(KEY).getString(KEY, "");
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();
        List<Product> favouriteList = gson.fromJson(favouriteListString, type);
        successCallback.onSuccess(favouriteList);
        if (favouriteList!= null) {
            Log.e("getShared", favouriteList.size() + " size");
            for (Product product:
                 favouriteList) {
                Log.e("product" , product.getName() + product.isLiked()+"");
            }
        }
    }
}
