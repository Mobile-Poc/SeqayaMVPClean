package com.mesawer.chaty.seqayamvpclean.data.datasource;

import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

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
