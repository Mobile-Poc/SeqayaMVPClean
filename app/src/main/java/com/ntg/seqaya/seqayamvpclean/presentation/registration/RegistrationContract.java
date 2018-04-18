package com.ntg.seqaya.seqayamvpclean.presentation.registration;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserAPI;

public interface RegistrationContract {

    interface View extends BaseView<Presenter> {
        void navigateToLoginActivity();
    }

    interface Presenter extends BasePresenter {
        void addNewUser(UserAPI user);
    }
}
