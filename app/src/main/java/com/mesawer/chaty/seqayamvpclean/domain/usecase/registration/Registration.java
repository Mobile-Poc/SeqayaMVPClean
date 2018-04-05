package com.mesawer.chaty.seqayamvpclean.domain.usecase.registration;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

import io.reactivex.Observable;

public class Registration implements UseCase<Registration.RequestValues, Registration.ResponseValues> {


    @Override
    public Observable<ResponseValues> execute(RequestValues requestValue) {
        return null;
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
