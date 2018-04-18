package com.ntg.seqaya.seqayamvpclean.domain.usecase.login;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;

public class FacebookLogin implements
        UseCase<FacebookLogin.RequestValues, FacebookLogin.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
    }

    public static final class RequestValues implements UseCase.RequestValues{

    }

    public static final class ResponseValues implements UseCase.ResponseValues{

    }
}
