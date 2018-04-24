package com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network;

import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.AddOrderResponse;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.FacebookLoginEntity;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.FacebookUser;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.GenericResponse;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.LoginEntity;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserApi;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Manufacturer;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.entity.SortBy;
import com.ntg.seqaya.seqayamvpclean.domain.entity.SortType;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiServices {

    @POST("verification/login")
    Call<UserApi> login(@Body LoginEntity loginEntity);

    @POST("verification/loginByFB")
    Call<FacebookUser> loginByFacebook(@Body FacebookLoginEntity facebookLoginEntity);

    @POST("verification/register")
    Call<GenericResponse> addNewUser(@Body UserApi userApi);

    @GET("user/getUserLocation")
    Call<List<Location>> getSavedLocations(@Header("X-AUTH-TOKEN") String userToken);

    @POST("user/addLocation")
    Call<GenericResponse> addNewLocation(@Header("X-AUTH-TOKEN") String userToken,
                                         @Body Location location);

    @POST("order")
    Call<AddOrderResponse> addNewOrder(@Header("X-AUTH-TOKEN") String userToken,
                                       @Body Order order);

    @GET("product/getManufacturers")
    Call<List<Manufacturer>> getManufacturer(@Header("X-AUTH-TOKEN") String userToken);

    @GET("product/search")
    Call<List<Product>> getProducts(@Header("X-AUTH-TOKEN") String userToken,
                                    @Query("manufacturerId") int manufacturerId,
                                    @Query("name") String name,
                                    @Query("priceFrom") double priceFrom,
                                    @Query("priceTo") double priceTo,
                                    // bottleSize and bpp is string contains values comma separated
                                    // ex. 40,50,60
                                    @Query("bottleSize") String bottleSize,
                                    @Query("bpp") String bpp,
                                    @Query("sortBy") @SortBy String sortBy,
                                    @Query("sortType") @SortType String sortType,
                                    @Query("pageSize") int pageSize,
                                    @Query("pageNumber") int pageNumber);
}
