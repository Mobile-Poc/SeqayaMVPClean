package com.mesawer.chaty.seqayamvpclean.data.datasource;

import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.entity.UserAPI;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

public interface UsersDataSource {

    void addNewUser(UserAPI user,
                    SuccessCallback<Void> successCallback,
                    ErrorCallback errorCallback);

    void emailPasswordLogin(String email, String password,
                            SuccessCallback<Void> successCallback,
                            ErrorCallback errorCallback);
}
