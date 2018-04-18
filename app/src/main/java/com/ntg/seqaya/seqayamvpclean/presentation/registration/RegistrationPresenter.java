package com.ntg.seqaya.seqayamvpclean.presentation.registration;

import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserAPI;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.registration.AddNewUser;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.registration.AddNewUser.RequestValues;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View registrationView;
    private AddNewUser addNewUser;

    public RegistrationPresenter(RegistrationContract.View registrationView, AddNewUser addNewUser) {
        this.registrationView = registrationView;
        this.addNewUser = addNewUser;
        this.registrationView.setPresenter(this);
    }

    @Override
    public void addNewUser(UserAPI user) {
        RequestValues requestValues = new RequestValues(user);
        addNewUser.execute(requestValues,
                v -> registrationView.navigateToLoginActivity(),
                registrationView::showErrorMessage);
    }
}
