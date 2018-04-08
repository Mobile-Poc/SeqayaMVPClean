package com.mesawer.chaty.seqayamvpclean.data.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.mesawer.chaty.seqayamvpclean.data.ProductsDataSource;
import com.mesawer.chaty.seqayamvpclean.data.remote.entity.APIError;
import com.mesawer.chaty.seqayamvpclean.data.remote.entity.Credential;
import com.mesawer.chaty.seqayamvpclean.data.remote.entity.UserAPI;
import com.mesawer.chaty.seqayamvpclean.data.remote.network.ApiClient;
import com.mesawer.chaty.seqayamvpclean.data.remote.network.ProductService;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository.SuccessCallback;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;

import java.io.IOException;
import java.util.List;

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
    public void getProducts(
            SuccessCallback<List<Product>> successCallback,
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
        ApiClient.getClient().create(ProductService.class)
                .addNewUser(user)
                .enqueue(new Callback<UserAPI>() {
                    @Override
                    public void onResponse(@NonNull Call<UserAPI> call,
                                           @NonNull Response<UserAPI> response) {
                        if (response.isSuccessful()) {
                            UserAPI userAPI = response.body();
                            if (userAPI != null)
                                successCallback.onSuccess(null);
                        } else {
                            try {
                                errorCallback.onError(apiErrMsg(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserAPI> call, @NonNull Throwable t) {
                        errorCallback.onError("تأكد من اتصال الانترنت");
                    }
                });
    }

    @Override
    public void emailPasswordLogin(String email, String password,
                                   SuccessCallback<Void> successCallback,
                                   ErrorCallback errorCallback) {
        Credential credential = new Credential(email, password);
        ApiClient.getClient().create(ProductService.class)
                .login(credential)
                .enqueue(new Callback<UserAPI>() {
                    @Override
                    public void onResponse(@NonNull Call<UserAPI> call,
                                           @NonNull Response<UserAPI> response) {
                        if (response.isSuccessful()) {
                            UserAPI userAPI = response.body();
                            if (userAPI != null) {
                                User.setEmail(userAPI.getEmail());
                                User.setPassword(userAPI.getPassword());
                                User.setName(userAPI.getName());
                                successCallback.onSuccess(null);
                            }
                        } else {
                            try {
                                errorCallback.onError(apiErrMsg(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserAPI> call, @NonNull Throwable t) {
                        errorCallback.onError("تأكد من اتصال الانترنت");
                    }
                });
    }

    @Override
    public void addNewOrder(Order order,
                            SuccessCallback<Order> successCallback,
                            ErrorCallback errorCallback) {

    }

    @Override
    public void getOrderHistory(SuccessCallback<List<Order>> successCallback,
                                ErrorCallback errorCallback) {
        ApiClient.getClient().create(ProductService.class)
                .getOrderHistory(User.getEmail())
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Order>> call,
                                           @NonNull Response<List<Order>> response) {
                        if (response.isSuccessful()){
                            List<Order> orders = response.body();
                            successCallback.onSuccess(orders);
                        } else {
                            try {
                                errorCallback.onError(apiErrMsg(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                        errorCallback.onError("تأكد من اتصال الانترنت");
                    }
                });
    }

    @Override
    public void addNewLocation(Location location,
                               SuccessCallback<Location> successCallback,
                               ErrorCallback errorCallback) {

    }

    @Override
    public void getSavedLocations(SuccessCallback<List<Location>> successCallback,
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
    public void getFavs(SuccessCallback<List<Product>> successCallback,
                        ErrorCallback errorCallback) {

    }

    private String apiErrMsg(String jsonString) {
        Gson gson = new Gson();
        APIError apiError = gson.fromJson(jsonString, APIError.class);
        return apiError.getMessage();
    }
}
