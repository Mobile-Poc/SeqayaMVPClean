package com.mesawer.chaty.seqayamvpclean.data.datasource.remote;

import com.mesawer.chaty.seqayamvpclean.data.datasource.FavouritesDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

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
        try {
            Response<Fav> response = ApiClient.getClient()
                    .create(ProductService.class)
                    .addFav(fav).execute();
            if (response.isSuccessful()) {
                successCallback.onSuccess(fav);
            }
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }

    @Override
    public void deleteFav(String productId,
                          SuccessCallback<Fav> successCallback,
                          ErrorCallback errorCallback) {
        try {
            Response<Fav> response = ApiClient.getClient()
                    .create(ProductService.class)
                    .deleteFav(productId).execute();
            if (response.isSuccessful()) {
                Fav fav = response.body();
                if (fav.getProductId().equals(productId)) {
                    successCallback.onSuccess(fav);
                }
            }
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }

    @Override
    public void getFavs(SuccessCallback<List<Product>> successCallback,
                        ErrorCallback errorCallback) {
        try {
            Response<List<Product>> response = ApiClient.getClient().create(ProductService.class)
                    .getFavs(User.getEmail()).execute();
            if (response.isSuccessful()) {
                List<Product> favs = response.body();
                successCallback.onSuccess(favs);
            }
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }
}
