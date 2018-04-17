package com.ntg.seqaya.seqayamvpclean.presentation.login;

import com.ntg.seqaya.seqayamvpclean.base.UseCaseHandler;
import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.login.EmailPasswordLogin;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.login.EmailPasswordLogin.RequestValues;

public class LoginPresenter implements LoginContract.Presenter {

    private UseCaseHandler useCaseHandler;
    private LoginContract.View loginView;
    private EmailPasswordLogin emailPasswordUseCase;

    public LoginPresenter(UseCaseHandler useCaseHandler,
                          LoginContract.View loginView,
                          EmailPasswordLogin emailPasswordUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.loginView = loginView;
        this.emailPasswordUseCase = emailPasswordUseCase;
        this.loginView.setPresenter(this);
    }

    @Override
    public void emailPasswordLogin(String email, String password) {
        RequestValues requestValues = new RequestValues(email, password);
        useCaseHandler.execute(emailPasswordUseCase, requestValues,
                new UseCase.UseCaseCallback<EmailPasswordLogin.ResponseValues>() {
            @Override
            public void onSuccess(EmailPasswordLogin.ResponseValues response) {
                loginView.navigateToMainActivity();
            }

            @Override
            public void onError(String errMsg) {
                loginView.showErrorMessage(errMsg);
            }
        });
    }
}
