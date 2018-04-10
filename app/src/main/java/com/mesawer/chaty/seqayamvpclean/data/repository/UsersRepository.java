package com.mesawer.chaty.seqayamvpclean.data.repository;

import com.mesawer.chaty.seqayamvpclean.data.datasource.UsersDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IUsersRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

public class UsersRepository implements IUsersRepository{

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
