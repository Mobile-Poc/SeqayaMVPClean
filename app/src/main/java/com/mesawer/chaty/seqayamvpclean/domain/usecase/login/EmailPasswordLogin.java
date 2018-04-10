package com.mesawer.chaty.seqayamvpclean.domain.usecase.login;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IUsersRepository;

public class EmailPasswordLogin implements UseCase<EmailPasswordLogin.RequestValues, EmailPasswordLogin.ResponseValues> {

    private IUsersRepository usersRepository;

    public EmailPasswordLogin(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        usersRepository.emailPasswordLogin(requestValue.getEmail(), requestValue.getPassword(),
                v -> successCallback.onSuccess(null),
                errorCallback::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
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

    public static final class ResponseValues implements UseCase.ResponseValues {
    }
}
