package com.mesawer.chaty.seqayamvpclean.data;

import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository.SuccessCallback;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;

import java.util.List;

public interface ProductsDataSource {
    void getProducts(SuccessCallback<List<Product>> successCallback, ErrorCallback errorCallback);

    void getSearchResult(String searchKeyword,
                         SuccessCallback<List<Product>> successCallback,
                         ErrorCallback errorCallback);

    void addNewUser(User user,
                    SuccessCallback<Void> successCallback,
                    ErrorCallback errorCallback);

    void emailPasswordLogin(String email, String password,
                            SuccessCallback<Void> successCallback,
                            ErrorCallback errorCallback);

    void addNewOrder(Order order,
                     SuccessCallback<Order> successCallback,
                     ErrorCallback errorCallback);

    void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                         ErrorCallback errorCallback);

    void addNewLocation(Location location,
                        SuccessCallback<Location> successCallback,
                        ErrorCallback errorCallback);

    void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                           ErrorCallback errorCallback);

    void addFav(Fav fav,
                SuccessCallback<Fav> successCallback,
                ErrorCallback errorCallback);

    void deleteFav(String productId,
                   SuccessCallback<Fav> successCallback,
                   ErrorCallback errorCallback);

    void getFavs(SuccessCallback<List<Product>> successCallback,
                 ErrorCallback errorCallback);
}
