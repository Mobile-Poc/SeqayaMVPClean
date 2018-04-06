package com.mesawer.chaty.seqayamvpclean.domain.usecase.login;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository;

import io.reactivex.Observable;

public class EmailPasswordLogin implements UseCase<EmailPasswordLogin.RequestValues, EmailPasswordLogin.ResponseValues> {

    private IProductsRepository productsRepository;

    public EmailPasswordLogin(IProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                                              UseCaseCallback<ResponseValues> useCaseCallback) {

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
    }
}
