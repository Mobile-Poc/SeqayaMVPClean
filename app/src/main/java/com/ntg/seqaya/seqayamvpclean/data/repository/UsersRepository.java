package com.ntg.seqaya.seqayamvpclean.data.repository;

import com.ntg.seqaya.seqayamvpclean.data.datasource.UsersDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserAPI;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IUsersRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

public class UsersRepository implements IUsersRepository {

    private static UsersRepository INSTANCE;
    private UsersDataSource usersDataSource;

    private UsersRepository(UsersDataSource usersDataSource) {
        this.usersDataSource = usersDataSource;
    }

    public static UsersRepository getInstance(UsersDataSource usersDataSource) {
        if (INSTANCE == null) INSTANCE = new UsersRepository(usersDataSource);
        return INSTANCE;
    }

    @Override
    public void addNewUser(UserAPI user,
                           SuccessCallback<Void> successCallback,
                           ErrorCallback errorCallback) {
        usersDataSource.addNewUser(user, successCallback, errorCallback);
    }

    @Override
    public void emailPasswordLogin(String email, String password,
                                   SuccessCallback<Void> successCallback,
                                   ErrorCallback errorCallback) {
        usersDataSource.emailPasswordLogin(email, password, successCallback, errorCallback);
    }
}
