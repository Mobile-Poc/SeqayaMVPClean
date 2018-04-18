package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import android.support.annotation.NonNull;

import com.ntg.seqaya.seqayamvpclean.data.datasource.UsersDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.Credential;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserAPI;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.Util.apiErrMsg;

public class UsersRemoteDataSource implements UsersDataSource {

    private static UsersRemoteDataSource INSTANCE;

    private UsersRemoteDataSource() {
    }

    public static UsersRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new UsersRemoteDataSource();
        return INSTANCE;
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
}
