package com.mesawer.chaty.seqayamvpclean.domain.usecase.registration;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class Registration implements UseCase<Registration.RequestValues, Registration.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseSuccessCallback<ResponseValues> successCallback, UseCaseErrorCallback errorCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
