package com.ntg.seqaya.seqayamvpclean.data.datasource;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

/**
 * Created by islam on 4/22/2018.
 */

public interface FavouriteLocalDataSource {
    void addFav(List<Product> favouriteList , Product favouritesProduct);

    void deleteFav(List<Product> favouriteList , Product favouritesProduct );

    void getFavs(SuccessCallback<List<Product>> successCallback,
                 ErrorCallback errorCall);
}
