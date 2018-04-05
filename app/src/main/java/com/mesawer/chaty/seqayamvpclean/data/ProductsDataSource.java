package com.mesawer.chaty.seqayamvpclean.data;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;

import java.util.List;

import io.reactivex.Observable;

public interface ProductsDataSource {
    Observable<List<Product>> getProducts();

    Observable<List<Product>> getSearchResult(String searchKeyword);

    Observable<User> addNewUser(User user);

    Observable<User> emailPasswordLogin(String email, String password);

    Observable<Order> addNewOrder(Order order);

    Observable<List<Order>> getOrderHistory(String userId);

    Observable<Location> addNewLocation(Location location);

    Observable<List<Location>> getSavedLocations(String userId);

    Observable<Fav> addFav(Fav fav);

    Observable<Fav> deleteFav(String productId);

    Observable<List<Product>> getFavs(String userId);
}
