package com.mesawer.chaty.seqayamvpclean.data.remote;

import com.mesawer.chaty.seqayamvpclean.data.ProductsDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository.SuccessCallback;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;

import java.util.List;

public class ProductsRemoteDataSource implements ProductsDataSource {

    private static ProductsRemoteDataSource INSTANCE;

    private ProductsRemoteDataSource() {
    }

    public static ProductsRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new ProductsRemoteDataSource();
        return INSTANCE;
    }

    @Override
    public void getProducts(
            SuccessCallback<List<Product>> successCallback,
            ErrorCallback errorCallback) {
    }

    @Override
    public void getSearchResult(String searchKeyword,
                                SuccessCallback<List<Product>> successCallback,
                                ErrorCallback errorCallback) {

    }

    @Override
    public void addNewUser(User user,
                           SuccessCallback<Void> successCallback,
                           ErrorCallback errorCallback) {

    }

    @Override
    public void emailPasswordLogin(String email, String password,
                                   SuccessCallback<Void> successCallback,
                                   ErrorCallback errorCallback) {

    }

    @Override
    public void addNewOrder(Order order,
                            SuccessCallback<Order> successCallback,
                            ErrorCallback errorCallback) {

    }

    @Override
    public void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                                ErrorCallback errorCallback) {

    }

    @Override
    public void addNewLocation(Location location,
                               SuccessCallback<Location> successCallback,
                               ErrorCallback errorCallback) {

    }

    @Override
    public void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                                  ErrorCallback errorCallback) {

    }

    @Override
    public void addFav(Fav fav,
                       SuccessCallback<Fav> successCallback,
                       ErrorCallback errorCallback) {

    }

    @Override
    public void deleteFav(String productId,
                          SuccessCallback<Fav> successCallback,
                          ErrorCallback errorCallback) {

    }

    @Override
    public void getFavs(SuccessCallback<List<Product>> successCallback,
                        ErrorCallback errorCallback) {

    }

}
