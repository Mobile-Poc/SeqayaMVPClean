package com.mesawer.chaty.seqayamvpclean.data.remote.network;

import com.mesawer.chaty.seqayamvpclean.data.remote.entity.Credential;
import com.mesawer.chaty.seqayamvpclean.data.remote.entity.UserAPI;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

import java.util.List;

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
    Call<List<Product>> getProducts();

    @GET("search")
    Call<List<Product>> getSearchResult(@Query("q") String searchKeyword);

    @POST("user")
    Call<UserAPI> addNewUser(@Body UserAPI user);

    @POST("login")
    Call<UserAPI> login(@Body Credential credential);

    @POST("order")
    Call<Order> addNewOrder(@Body Order order);

    @GET("order/{userId}")
    Call<List<Order>> getOrderHistory(@Path("userId") String userId);

    @POST("location")
    Call<Location> addNewLocation(@Body Location location);

    @GET("location/{userId}")
    Call<List<Location>> getSavedLocations(@Path("userId") String userId);

    @POST("fav")
    Call<Fav> addFav(@Body Fav fav);

    @GET("fav/{userId}")
    Call<List<Product>> getFavs(@Path("userId") String userId);

    @DELETE("fav/{id}")
    Call<Fav> deleteFav(@Path("id") String productId);
}
