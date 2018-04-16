package com.ntg.seqaya.seqayamvpclean.domain.usecase.login;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IUsersRepository;

public class EmailPasswordLogin extends
        UseCase<EmailPasswordLogin.RequestValues, EmailPasswordLogin.ResponseValues> {

    private IUsersRepository usersRepository;

    public EmailPasswordLogin(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        usersRepository.emailPasswordLogin(requestValues.getEmail(), requestValues.getPassword(),
                v -> getUseCaseCallback().onSuccess(null),
                getUseCaseCallback()::onError);
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

    public static final class ResponseValues implements UseCase.ResponseValues {}
}
