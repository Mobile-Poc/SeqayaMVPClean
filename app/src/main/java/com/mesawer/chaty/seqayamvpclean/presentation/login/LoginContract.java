package com.mesawer.chaty.seqayamvpclean.presentation.login;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void navigateToMainActivity();

        void navigateToRegistrationActivity();
    }

    interface Presenter extends BasePresenter {
        void emailPasswordLogin(String email, String password);
    }
}
