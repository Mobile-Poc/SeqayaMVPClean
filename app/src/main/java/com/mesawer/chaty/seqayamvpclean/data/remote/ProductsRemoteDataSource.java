package com.mesawer.chaty.seqayamvpclean.data.remote;

import android.support.annotation.NonNull;

import com.mesawer.chaty.seqayamvpclean.data.ProductsDataSource;
import com.mesawer.chaty.seqayamvpclean.data.remote.network.ApiClient;
import com.mesawer.chaty.seqayamvpclean.data.remote.network.ProductService;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        return Observable.create(emitter -> {
            ApiClient.getClient().create(ProductService.class)
                    .getProducts()
                    .enqueue(new Callback<List<Product>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Product>> call,
                                               @NonNull Response<List<Product>> response) {
                            List<Product> products = response.body();
                            emitter.onNext(products);
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Product>> call,
                                              @NonNull Throwable t) {

                        }
                    });
        });
    }

    @Override
    public Observable<List<Product>> getSearchResult(String searchKeyword) {
        return null;
    }

    @Override
    public Observable<User> addNewUser(User user) {
        return null;
    }

    @Override
    public Observable<User> emailPasswordLogin(String email, String password) {
        return null;
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
