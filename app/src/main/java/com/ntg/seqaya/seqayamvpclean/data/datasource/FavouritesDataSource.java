package com.ntg.seqaya.seqayamvpclean.data.datasource;

import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public interface FavouritesDataSource {

    void addFav(Fav fav,
                SuccessCallback<Fav> successCallback,
                ErrorCallback errorCallback);

    void deleteFav(String productId,
                   SuccessCallback<Fav> successCallback,
                   ErrorCallback errorCallback);

    void getFavs(SuccessCallback<List<Product>> successCallback,
                 ErrorCallback errorCallback);
}
