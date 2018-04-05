package com.mesawer.chaty.seqayamvpclean.data.remote;

import com.mesawer.chaty.seqayamvpclean.data.ProductsDataSource;
import com.mesawer.chaty.seqayamvpclean.data.remote.entity.Credential;
import com.mesawer.chaty.seqayamvpclean.data.remote.network.ApiClient;
import com.mesawer.chaty.seqayamvpclean.data.remote.network.ProductService;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductsRemoteDataSource implements ProductsDataSource {

    private static ProductsRemoteDataSource INSTANCE;

    private ProductsRemoteDataSource() {
    }

    public static ProductsRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new ProductsRemoteDataSource();
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
        return Completable.create(emitter ->{
            Credential credential = new Credential(email, password);
            ApiClient.getClient().create(ProductService.class)
                    .login(credential)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(userAPI -> {
                        User.setEmail(userAPI.getEmail());
                        User.setName(userAPI.getName());
                        User.setPassword(userAPI.getPassword());
                        emitter.onComplete();
                    }, emitter::onError);
        });
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
