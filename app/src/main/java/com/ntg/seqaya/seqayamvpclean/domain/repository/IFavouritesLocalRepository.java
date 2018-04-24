package com.ntg.seqaya.seqayamvpclean.domain.repository;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;

import java.util.List;

/**
 * Created by islam on 4/22/2018.
 */

public interface IFavouritesLocalRepository {
    void addFav(List<Product> favouriteList , Product fav);

    void deleteFav(List<Product> favouriteList , Product fav );

    void getFavs(SuccessCallback<List<Product>> successCallback,
                 ErrorCallback errorCallback);
}
