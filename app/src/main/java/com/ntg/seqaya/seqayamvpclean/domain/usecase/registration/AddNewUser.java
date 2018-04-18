package com.ntg.seqaya.seqayamvpclean.domain.usecase.registration;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserAPI;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IUsersRepository;

public class AddNewUser implements UseCase<AddNewUser.RequestValues, AddNewUser.ResponseValues> {

    private IUsersRepository usersRepository;

    public AddNewUser(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        usersRepository.addNewUser(requestValue.getUser(),
                v -> successCallback.onSuccess(null),
                errorCallback::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private UserAPI user;

        public RequestValues(UserAPI user) {
            this.user = user;
        }

        public UserAPI getUser() {
            return user;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
    }
}
