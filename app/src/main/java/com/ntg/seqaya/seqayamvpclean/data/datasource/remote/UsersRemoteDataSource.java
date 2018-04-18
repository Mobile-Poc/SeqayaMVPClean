package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import android.util.Log;

import com.ntg.seqaya.seqayamvpclean.data.datasource.UsersDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.Credential;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserAPI;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.io.IOException;

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
        try {
            Response<UserAPI> response = ApiClient.getClient().create(ProductService.class)
                    .addNewUser(user).execute();
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
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }

    @Override
    public void emailPasswordLogin(String email, String password,
                                   SuccessCallback<Void> successCallback,
                                   ErrorCallback errorCallback) {
        Credential credential = new Credential(email, password);
        try {
            Response<UserAPI> response = ApiClient.getClient().create(ProductService.class)
                    .login(credential).execute();
            if (response.isSuccessful()) {
                UserAPI userAPI = response.body();
                if (userAPI != null) {
                    User.setEmail(userAPI.getEmail());
                    User.setPassword(userAPI.getPassword());
                    User.setName(userAPI.getName());
                    successCallback.onSuccess(null);
                }
            } else {
                if (password.equals("")) {
                    UserAPI user = new UserAPI();
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setName("");
                    Response<UserAPI> userResponse = ApiClient.getClient()
                            .create(ProductService.class)
                            .addNewUser(user)
                            .execute();
                    if (userResponse.isSuccessful()) {
                        UserAPI userAPI = userResponse.body();
                        if (userAPI != null) {
                            User.setEmail(userAPI.getEmail());
                            User.setPassword(userAPI.getPassword());
                            User.setName(userAPI.getName());
                            successCallback.onSuccess(null);
                        }
                    }
                } else {
                    try {
                        errorCallback.onError(apiErrMsg(response.errorBody().string()));
                    } catch (IOException e) {
                        Log.d("apiErrMsg", e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }
}
