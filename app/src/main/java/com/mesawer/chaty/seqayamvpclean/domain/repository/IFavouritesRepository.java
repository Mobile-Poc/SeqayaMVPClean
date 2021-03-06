package com.mesawer.chaty.seqayamvpclean.domain.repository;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

import java.util.List;

public interface IFavouritesRepository {

    void addFav(Fav fav,
                SuccessCallback<Fav> successCallback,
                ErrorCallback errorCallback);

    void deleteFav(String productId,
                   SuccessCallback<Fav> successCallback,
                   ErrorCallback errorCallback);

    void getFavs(SuccessCallback<List<Product>> successCallback,
                 ErrorCallback errorCallback);
}
