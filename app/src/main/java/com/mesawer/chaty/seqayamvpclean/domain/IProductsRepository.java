package com.mesawer.chaty.seqayamvpclean.domain;

import java.util.List;

import com.mesawer.chaty.seqayamvpclean.data.remote.entity.UserAPI;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

public interface IProductsRepository {
    void getProducts(SuccessCallback<List<Product>> successCallback, ErrorCallback errorCallback);

    void getSearchResult(String searchKeyword,
                         SuccessCallback<List<Product>> successCallback,
                         ErrorCallback errorCallback);

    void addNewUser(UserAPI user,
                    SuccessCallback<Void> successCallback,
                    ErrorCallback errorCallback);

    void emailPasswordLogin(String email, String password,
                            SuccessCallback<Void> successCallback,
                            ErrorCallback errorCallback);

    void addNewOrder(Order order,
                     SuccessCallback<Order> successCallback,
                     ErrorCallback errorCallback);

    void getOrderHistory(String userId,
                         SuccessCallback<List<Order>> successCallback,
                         ErrorCallback errorCallback);

    void addNewLocation(Location location,
                        SuccessCallback<Location> successCallback,
                        ErrorCallback errorCallback);

    void getSavedLocations(String userId,
                           SuccessCallback<List<Location>> successCallback,
                           ErrorCallback errorCallback);

    void addFav(Fav fav,
                SuccessCallback<Fav> successCallback,
                ErrorCallback errorCallback);

    void deleteFav(String productId,
                   SuccessCallback<Fav> successCallback,
                   ErrorCallback errorCallback);

    void getFavs(String userId,
                 SuccessCallback<List<Product>> successCallback,
                 ErrorCallback errorCallback);

    interface SuccessCallback<T> {
        void onSuccess(T result);
    }

    interface ErrorCallback {
        void onError(String errMsg);
    }
}
