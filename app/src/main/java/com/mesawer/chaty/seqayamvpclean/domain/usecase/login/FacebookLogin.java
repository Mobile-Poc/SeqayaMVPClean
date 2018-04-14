package com.mesawer.chaty.seqayamvpclean.domain.usecase.login;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class FacebookLogin extends
        UseCase<FacebookLogin.RequestValues,FacebookLogin.ResponseValues> {


    @Override
    protected void executeUseCase(RequestValues requestValues) {

    }

    public static final class RequestValues implements UseCase.RequestValues{

    }

    public static final class ResponseValues implements UseCase.ResponseValues{

    }
}
