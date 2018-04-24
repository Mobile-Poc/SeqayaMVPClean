package com.ntg.seqaya.seqayamvpclean.data.repository;

import com.ntg.seqaya.seqayamvpclean.data.datasource.FavouriteLocalDataSource;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IFavouritesLocalRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;



public class FavouritesLocalRepository implements IFavouritesLocalRepository {

    private static FavouritesLocalRepository INSTANCE;
    private FavouriteLocalDataSource favouriteLocalDataSource;

    private FavouritesLocalRepository(FavouriteLocalDataSource favouriteLocalDataSource) {
        this.favouriteLocalDataSource = favouriteLocalDataSource;

    }

    public static FavouritesLocalRepository getInstance(FavouriteLocalDataSource favouriteLocalDataSource) {
        if (INSTANCE == null) INSTANCE = new FavouritesLocalRepository(favouriteLocalDataSource);
        return INSTANCE;
    }
    @Override
    public void addFav(List<Product> favouriteList , Product fav) {
        favouriteLocalDataSource.addFav(favouriteList , fav);

    }

    @Override
    public void deleteFav(List<Product> favouriteList , Product fav ) {
        favouriteLocalDataSource.deleteFav(favouriteList , fav );
    }

    @Override
    public void getFavs(SuccessCallback<List<Product>> successCallback, ErrorCallback errorCallback) {
        favouriteLocalDataSource.getFavs(successCallback, errorCallback);

    }
}
