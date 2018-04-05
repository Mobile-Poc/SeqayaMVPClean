package com.mesawer.chaty.seqayamvpclean.data.remote.network;

import com.mesawer.chaty.seqayamvpclean.data.remote.entity.Credential;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.data.remote.entity.UserAPI;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by islam on 3/26/2018.
 */

public interface ProductService {

    @GET("product")
    Observable<List<Product>> getProducts();

    @GET("search")
    Observable<List<Product>> getSearchResult(@Query("q") String searchKeyword);

    @POST("user")
    Observable<UserAPI> addNewUser(@Body UserAPI user);

    @POST("login")
    Observable<UserAPI> login(@Body Credential credential);

    @POST("order")
    Observable<Order> addNewOrder(@Body Order order);

    @GET("order/{userId}")
    Observable<List<Order>> getOrderHistory(@Path("userId") String userId);

    @POST("location")
    Observable<Location> addNewLocation(@Body Location location);

    @GET("location/{userId}")
    Observable<List<Location>> getSavedLocations(@Path("userId") String userId);

    @POST("fav")
    Observable<Fav> addFav(@Body Fav fav);

    @GET("fav/{userId}")
    Observable<List<Product>> getFavs(@Path("userId") String userId);

    @DELETE("fav/{id}")
    Observable<Fav> deleteFav(@Path("id") String productId);
}
