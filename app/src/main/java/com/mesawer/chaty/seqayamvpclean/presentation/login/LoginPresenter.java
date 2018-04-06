package com.mesawer.chaty.seqayamvpclean.presentation.login;

import com.mesawer.chaty.seqayamvpclean.domain.usecase.login.EmailPasswordLogin;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.login.EmailPasswordLogin.RequestValues;

import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private EmailPasswordLogin emailPasswordUseCase;
    private Disposable emailPasswordDisposable;

    public LoginPresenter(LoginContract.View loginView, EmailPasswordLogin emailPasswordUseCase) {
        this.loginView = loginView;
        this.emailPasswordUseCase = emailPasswordUseCase;
        this.loginView.setPresenter(this);
    }

    @Override
    public void emailPasswordLogin(String email, String password) {
        RequestValues requestValues = new RequestValues(email, password);

    }
}
