package com.mesawer.chaty.seqayamvpclean.domain.usecase.login;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class TwitterLogin implements UseCase<TwitterLogin.RequestValues, TwitterLogin.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseCallback<ResponseValues> useCaseCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
