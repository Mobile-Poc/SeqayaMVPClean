package com.mesawer.chaty.seqayamvpclean.data.repository;

import com.mesawer.chaty.seqayamvpclean.data.datasource.FavouritesDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IFavouritesRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public class FavouritesRepository implements IFavouritesRepository {

    private static FavouritesRepository INSTANCE;
    private FavouritesDataSource favouritesDataSource;

    private FavouritesRepository(FavouritesDataSource favouritesDataSource) {
        this.favouritesDataSource = favouritesDataSource;
    }

    public static FavouritesRepository getInstance(FavouritesDataSource favouritesDataSource) {
        if (INSTANCE == null) INSTANCE = new FavouritesRepository(favouritesDataSource);
        return INSTANCE;
    }

    @Override
    public void addFav(Fav fav, SuccessCallback<Fav> successCallback,
                       ErrorCallback errorCallback) {
        favouritesDataSource.addFav(fav , successCallback , errorCallback);
    }

    @Override
    public void deleteFav(String productId, SuccessCallback<Fav> successCallback,
                          ErrorCallback errorCallback) {
        favouritesDataSource.deleteFav(productId , successCallback , errorCallback);
    }

    @Override
    public void getFavs(SuccessCallback<List<Product>> successCallback,
                        ErrorCallback errorCallback) {
        favouritesDataSource.getFavs(successCallback, errorCallback);
    }
}
