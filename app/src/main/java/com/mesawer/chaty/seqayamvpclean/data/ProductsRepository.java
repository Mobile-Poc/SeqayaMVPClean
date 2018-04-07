package com.mesawer.chaty.seqayamvpclean.data;

import com.mesawer.chaty.seqayamvpclean.data.remote.entity.UserAPI;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

import java.util.List;

public class ProductsRepository implements IProductsRepository {

    private static ProductsRepository INSTANCE;
    private ProductsDataSource productsDataSource;

    private ProductsRepository(ProductsDataSource productsDataSource) {
        this.productsDataSource = productsDataSource;
    }

    public static ProductsRepository getInstance(ProductsDataSource productsDataSource) {
        if (INSTANCE == null) INSTANCE = new ProductsRepository(productsDataSource);
        return INSTANCE;
    }

    @Override
    public void getProducts(SuccessCallback<List<Product>> successCallback,
                            ErrorCallback errorCallback) {

    }

    @Override
    public void getSearchResult(String searchKeyword,
                                SuccessCallback<List<Product>> successCallback,
                                ErrorCallback errorCallback) {

    }

    @Override
    public void addNewUser(UserAPI user,
                           SuccessCallback<Void> successCallback,
                           ErrorCallback errorCallback) {
        productsDataSource.addNewUser(user, successCallback, errorCallback);
    }

    @Override
    public void emailPasswordLogin(String email, String password,
                                   SuccessCallback<Void> successCallback,
                                   ErrorCallback errorCallback) {
        productsDataSource.emailPasswordLogin(email, password, successCallback, errorCallback);
    }

    @Override
    public void addNewOrder(Order order,
                            SuccessCallback<Order> successCallback,
                            ErrorCallback errorCallback) {

    }

    @Override
    public void getOrderHistory(String userId,
                                SuccessCallback<List<Order>> successCallback,
                                ErrorCallback errorCallback) {

    }

    @Override
    public void addNewLocation(Location location,
                               SuccessCallback<Location> successCallback,
                               ErrorCallback errorCallback) {

    }

    @Override
    public void getSavedLocations(String userId,
                                  SuccessCallback<List<Location>> successCallback,
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
    public void getFavs(String userId,
                        SuccessCallback<List<Product>> successCallback,
                        ErrorCallback errorCallback) {

    }
}
