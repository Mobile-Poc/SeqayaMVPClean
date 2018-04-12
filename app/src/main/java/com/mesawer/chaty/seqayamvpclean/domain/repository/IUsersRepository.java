package com.mesawer.chaty.seqayamvpclean.domain.repository;

import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.entity.UserAPI;

public interface IUsersRepository {

    void addNewUser(UserAPI user,
                    SuccessCallback<Void> successCallback,
                    ErrorCallback errorCallback);

    void emailPasswordLogin(String email, String password,
                            SuccessCallback<Void> successCallback,
                            ErrorCallback errorCallback);
}
