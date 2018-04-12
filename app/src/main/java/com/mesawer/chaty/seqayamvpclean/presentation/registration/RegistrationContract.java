package com.mesawer.chaty.seqayamvpclean.presentation.registration;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.entity.UserAPI;

public interface RegistrationContract {

    interface View extends BaseView<Presenter> {
        void navigateToLoginActivity();
    }

    interface Presenter extends BasePresenter {
        void addNewUser(UserAPI user);
    }
}
