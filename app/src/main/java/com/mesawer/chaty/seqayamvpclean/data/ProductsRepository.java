package com.mesawer.chaty.seqayamvpclean.data;

import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

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
    public Observable<List<Product>> getProducts() {
        return null;
    }

    @Override
    public Observable<List<Product>> getSearchResult(String searchKeyword) {
        return null;
    }

    @Override
    public Completable addNewUser(User user) {
        return null;
    }

    @Override
    public Completable emailPasswordLogin(String email, String password) {
        return productsDataSource.emailPasswordLogin(email, password);
    }

    @Override
    public Observable<Order> addNewOrder(Order order) {
        return null;
    }

    @Override
    public Observable<List<Order>> getOrderHistory(String userId) {
        return null;
    }

    @Override
    public Observable<Location> addNewLocation(Location location) {
        return null;
    }

    @Override
    public Observable<List<Location>> getSavedLocations(String userId) {
        return null;
    }

    @Override
    public Observable<Fav> addFav(Fav fav) {
        return null;
    }

    @Override
    public Observable<Fav> deleteFav(String productId) {
        return null;
    }

    @Override
    public Observable<List<Product>> getFavs(String userId) {
        return null;
    }
}
