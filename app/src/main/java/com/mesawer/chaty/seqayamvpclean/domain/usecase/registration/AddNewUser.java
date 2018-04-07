package com.mesawer.chaty.seqayamvpclean.domain.usecase.registration;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.data.remote.entity.UserAPI;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository;

public class AddNewUser implements UseCase<AddNewUser.RequestValues, AddNewUser.ResponseValues> {

    private IProductsRepository productsRepository;

    public AddNewUser(IProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        productsRepository.addNewUser(requestValue.getUser(),
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
