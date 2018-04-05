package com.mesawer.chaty.seqayamvpclean.presentation.login;

import com.mesawer.chaty.seqayamvpclean.domain.usecase.login.EmailPasswordLogin;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.login.EmailPasswordLogin.RequestValues;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View loginView;
    private EmailPasswordLogin emailPasswordUseCase;

    public LoginPresenter(LoginContract.View loginView, EmailPasswordLogin emailPasswordUseCase) {
        this.loginView = loginView;
        this.emailPasswordUseCase = emailPasswordUseCase;
    }

    @Override
    public void emailPasswordLogin(String email, String password) {
         RequestValues requestValues = new RequestValues(email, password);
        emailPasswordUseCase.execute(requestValues)
                .subscribe(responseValues -> {
                    if (responseValues.isSuccessLogin()){
                        loginView.navigateToMainActivity();
                    }else {
                        
                    }
                });
    }
}
