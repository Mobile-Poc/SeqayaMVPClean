package com.ntg.seqaya.seqayamvpclean.presentation.registration;

import com.ntg.seqaya.seqayamvpclean.base.UseCaseHandler;
import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserApi;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.registration.AddNewUser;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.registration.AddNewUser.RequestValues;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private UseCaseHandler useCaseHandler;
    private RegistrationContract.View registrationView;
    private AddNewUser addNewUser;

    public RegistrationPresenter(UseCaseHandler useCaseHandler,
                                 RegistrationContract.View registrationView,
                                 AddNewUser addNewUser) {
        this.useCaseHandler = useCaseHandler;
        this.registrationView = registrationView;
        this.addNewUser = addNewUser;
    }

    @Override
    public void addNewUser(UserApi user) {
        RequestValues requestValues = new RequestValues(user);
        useCaseHandler.execute(addNewUser, requestValues,
                new UseCase.UseCaseCallback<AddNewUser.ResponseValues>() {
            @Override
            public void onSuccess(AddNewUser.ResponseValues response) {
                registrationView.navigateToLoginActivity();
            }

            @Override
            public void onError(String errMsg) {
                registrationView.showErrorMessage(errMsg);
            }
        });
    }
}
