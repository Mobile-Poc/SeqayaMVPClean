package com.mesawer.chaty.seqayamvpclean.domain.repository;

import com.mesawer.chaty.seqayamvpclean.domain.entity.User;

public interface IUsersRepository {

    void addNewUser(User user,
                    SuccessCallback<Void> successCallback,
                    ErrorCallback errorCallback);

    void emailPasswordLogin(String email, String password,
                            SuccessCallback<Void> successCallback,
                            ErrorCallback errorCallback);
}
