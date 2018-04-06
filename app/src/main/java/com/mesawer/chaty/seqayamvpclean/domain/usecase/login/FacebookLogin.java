package com.mesawer.chaty.seqayamvpclean.domain.usecase.login;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class FacebookLogin implements UseCase<FacebookLogin.RequestValues, FacebookLogin.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseCallback<ResponseValues> useCaseCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
