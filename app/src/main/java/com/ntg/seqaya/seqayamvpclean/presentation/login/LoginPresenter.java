package com.ntg.seqaya.seqayamvpclean.presentation.login;

import com.ntg.seqaya.seqayamvpclean.domain.usecase.login.EmailPasswordLogin;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.login.EmailPasswordLogin.RequestValues;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private EmailPasswordLogin emailPasswordUseCase;

    public LoginPresenter(LoginContract.View loginView, EmailPasswordLogin emailPasswordUseCase) {
        this.loginView = loginView;
        this.emailPasswordUseCase = emailPasswordUseCase;
        this.loginView.setPresenter(this);
    }

    @Override
    public void emailPasswordLogin(String email, String password) {
        RequestValues requestValues = new RequestValues(email, password);
        emailPasswordUseCase.execute(requestValues,
                v -> loginView.navigateToMainActivity(),
                loginView::showErrorMessage);
    }
}
