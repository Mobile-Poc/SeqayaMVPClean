package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import android.support.annotation.NonNull;

import com.ntg.seqaya.seqayamvpclean.data.datasource.FavouritesDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.Util.apiErrMsg;

public class FavouritesRemoteDataSource implements FavouritesDataSource {

    private static FavouritesRemoteDataSource INSTANCE;

    private FavouritesRemoteDataSource() {
    }

    public static FavouritesRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new FavouritesRemoteDataSource();
        return INSTANCE;
    }

    @Override
    public void addFav(Fav fav,
                       SuccessCallback<Fav> successCallback,
                       ErrorCallback errorCallback) {
        ApiClient.getClient()
                .create(ProductService.class)
                .addFav(fav)
                .enqueue(new Callback<Fav>() {
                    @Override
                    public void onResponse(@NonNull Call<Fav> call, @NonNull Response<Fav> response) {
                        if(response.isSuccessful()){
                            successCallback.onSuccess(fav);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Fav> call, @NonNull Throwable t) {
                        errorCallback.onError(t.getMessage());
                    }
                });

    }

    @Override
    public void deleteFav(String productId,
                          SuccessCallback<Fav> successCallback,
                          ErrorCallback errorCallback) {
        ApiClient.getClient()
                .create(ProductService.class)
                .deleteFav(productId)
                .enqueue(new Callback<Fav>() {
                    @Override
                    public void onResponse(@NonNull Call<Fav> call, @NonNull Response<Fav> response) {
                        if (response.isSuccessful()){
                            successCallback.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Fav> call, @NonNull Throwable t) {
                        errorCallback.onError(t.getMessage());
                    }
                });

    }

    @Override
    public void getFavs(SuccessCallback<List<Product>> successCallback,
                        ErrorCallback errorCallback) {
        ApiClient.getClient().create(ProductService.class)
                .getFavs(User.getEmail())
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Product>> call,
                                           @NonNull Response<List<Product>> response) {
                        if (response.isSuccessful()){
                            List<Product> favs = response.body();
                            successCallback.onSuccess(favs);
                        } else {
                            try {
                                errorCallback.onError(apiErrMsg(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {

                    }
                });
    }
}
