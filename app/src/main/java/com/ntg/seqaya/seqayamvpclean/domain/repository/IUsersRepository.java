package com.ntg.seqaya.seqayamvpclean.domain.repository;

import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserApi;

public interface IUsersRepository {

    void addNewUser(UserApi user,
                    SuccessCallback<Void> successCallback,
                    ErrorCallback errorCallback);

    void emailPasswordLogin(String email, String password,
                            SuccessCallback<Void> successCallback,
                            ErrorCallback errorCallback);
}
