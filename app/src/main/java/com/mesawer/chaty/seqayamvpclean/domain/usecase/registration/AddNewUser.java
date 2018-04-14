package com.mesawer.chaty.seqayamvpclean.domain.usecase.registration;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.entity.UserAPI;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IUsersRepository;

public class AddNewUser extends UseCase<AddNewUser.RequestValues, AddNewUser.ResponseValues> {

    private IUsersRepository usersRepository;

    public AddNewUser(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        usersRepository.addNewUser(requestValues.getUser(),
                v -> getUseCaseCallback().onSuccess(null),
                getUseCaseCallback()::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private UserAPI user;

        public RequestValues(UserAPI user) {
            this.user = user;
        }

        public UserAPI getUser() {
            return user;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
    }
}
