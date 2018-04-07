package com.mesawer.chaty.seqayamvpclean.presentation.registration;

import com.mesawer.chaty.seqayamvpclean.data.remote.entity.UserAPI;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.registration.AddNewUser;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.registration.AddNewUser.RequestValues;

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
