package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import com.ntg.seqaya.seqayamvpclean.data.datasource.UsersDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserApi;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

public class UsersRemoteDataSource implements UsersDataSource {

    private static UsersRemoteDataSource INSTANCE;

    private UsersRemoteDataSource() {
    }

    public static UsersRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new UsersRemoteDataSource();
        return INSTANCE;
    }

    @Override
    public void addNewUser(UserApi user,
                           SuccessCallback<Void> successCallback,
                           ErrorCallback errorCallback) {
//        try {
//            Response<UserApi> response = ApiClient.getClient().create(ProductService.class)
//                    .addNewUser(user).execute();
//            if (response.isSuccessful()) {
//                UserApi userAPI = response.body();
//                if (userAPI != null)
//                    successCallback.onSuccess(null);
//            } else {
//                try {
//                    errorCallback.onError(apiErrMsg(response.errorBody().string()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
//            errorCallback.onError("تأكد من اتصال الانترنت");
//        }
    }

    @Override
    public void emailPasswordLogin(String email, String password,
                                   SuccessCallback<Void> successCallback,
                                   ErrorCallback errorCallback) {
//        Credential credential = new Credential(email, password);
//        try {
//            Response<UserApi> response = ApiClient.getClient().create(ProductService.class)
//                    .login(credential).execute();
//            if (response.isSuccessful()) {
//                UserApi userAPI = response.body();
//                if (userAPI != null) {
//                    User.setEmail(userAPI.getEmail());
//                    User.setPassword(userAPI.getPassword());
//                    User.setName(userAPI.getName());
//                    successCallback.onSuccess(null);
//                }
//            } else {
//                if (password.equals("")) {
//                    UserApi user = new UserApi();
//                    user.setEmail(email);
//                    user.setPassword(password);
//                    user.setName("");
//                    Response<UserApi> userResponse = ApiClient.getClient()
//                            .create(ProductService.class)
//                            .addNewUser(user)
//                            .execute();
//                    if (userResponse.isSuccessful()) {
//                        UserApi userAPI = userResponse.body();
//                        if (userAPI != null) {
//                            User.setEmail(userAPI.getEmail());
//                            User.setPassword(userAPI.getPassword());
//                            User.setName(userAPI.getName());
//                            successCallback.onSuccess(null);
//                        }
//                    }
//                } else {
//                    try {
//                        errorCallback.onError(apiErrMsg(response.errorBody().string()));
//                    } catch (IOException e) {
//                        Log.d("apiErrMsg", e.getMessage());
//                    }
//                }
//            }
//        } catch (IOException e) {
//            errorCallback.onError("تأكد من اتصال الانترنت");
//        }
    }
}
