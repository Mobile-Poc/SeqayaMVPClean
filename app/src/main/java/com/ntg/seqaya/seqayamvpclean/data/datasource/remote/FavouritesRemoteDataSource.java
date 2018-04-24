package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import com.ntg.seqaya.seqayamvpclean.data.datasource.FavouritesDataSource;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

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
//        try {
//            Response<Fav> response = ApiClient.getClient()
//                    .create(ProductService.class)
//                    .addFav(fav).execute();
//            if (response.isSuccessful()) {
//                successCallback.onSuccess(fav);
//            }
//        } catch (IOException e) {
//            errorCallback.onError("تأكد من اتصال الانترنت");
//        }
    }

    @Override
    public void deleteFav(String productId,
                          SuccessCallback<Fav> successCallback,
                          ErrorCallback errorCallback) {
//        try {
//            Response<Fav> response = ApiClient.getClient()
//                    .create(ProductService.class)
//                    .deleteFav(productId).execute();
//            if (response.isSuccessful()) {
//                Fav fav = response.body();
//                if (fav.getProductId().equals(productId)) {
//                    successCallback.onSuccess(fav);
//                }
//            }
//        } catch (IOException e) {
//            errorCallback.onError("تأكد من اتصال الانترنت");
//        }
    }

    @Override
    public void getFavs(SuccessCallback<List<Product>> successCallback,
                        ErrorCallback errorCallback) {
//        try {
//            Response<List<Product>> response = ApiClient.getClient().create(ProductService.class)
//                    .getFavs(User.getEmail()).execute();
//            if (response.isSuccessful()) {
//                List<Product> favs = response.body();
//                successCallback.onSuccess(favs);
//            }
//        } catch (IOException e) {
//            errorCallback.onError("تأكد من اتصال الانترنت");
//        }
    }
}
