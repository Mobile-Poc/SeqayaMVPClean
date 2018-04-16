package com.ntg.seqaya.seqayamvpclean.presentation.login;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void navigateToMainActivity();

        void navigateToRegistrationActivity();
    }

    interface Presenter extends BasePresenter {
        void emailPasswordLogin(String email, String password);
    }
}
