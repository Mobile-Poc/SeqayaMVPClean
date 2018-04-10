package com.mesawer.chaty.seqayamvpclean.data.datasource.remote;

import com.mesawer.chaty.seqayamvpclean.data.datasource.UsersDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

public class UsersRemoteDataSource implements UsersDataSource {

    private static UsersRemoteDataSource INSTANCE;

    private UsersRemoteDataSource() {
    }

    public static UsersRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new UsersRemoteDataSource();
        return INSTANCE;
    }

    @Override
    public void addNewUser(User user,
                           SuccessCallback<Void> successCallback,
                           ErrorCallback errorCallback) {

    }

    @Override
    public void emailPasswordLogin(String email, String password,
                                   SuccessCallback<Void> successCallback,
                                   ErrorCallback errorCallback) {

    }
}
