package com.mesawer.chaty.seqayamvpclean.domain.usecase.login;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository;

import io.reactivex.Observable;

public class EmailPasswordLogin implements UseCase<EmailPasswordLogin.RequestValues, EmailPasswordLogin.ResponseValues> {

    private IProductsRepository productsRepository;

    @Override
    public Observable<ResponseValues> execute(RequestValues requestValue) {
        return Observable.create(emitter -> {
        ResponseValues responseValues = new ResponseValues();
            productsRepository.emailPasswordLogin(requestValue.getEmail(), requestValue.getPassword())
                    .subscribe(() -> responseValues.setSuccessLogin(true),
                            throwable -> responseValues.setErrMsg(throwable.getMessage()));
            emitter.onNext(responseValues);
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String email;
        private String password;

        public RequestValues(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues{
        private boolean isSuccessLogin;
        private String errMsg;

        public ResponseValues() {
        }

        public void setSuccessLogin(boolean successLogin) {
            isSuccessLogin = successLogin;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public boolean isSuccessLogin() {
            return isSuccessLogin;
        }

        public String getErrMsg() {
            return errMsg;
        }
    }
}
