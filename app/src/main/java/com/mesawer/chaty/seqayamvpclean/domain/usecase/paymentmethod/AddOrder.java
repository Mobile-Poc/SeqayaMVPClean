package com.mesawer.chaty.seqayamvpclean.domain.usecase.paymentmethod;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

import io.reactivex.Observable;

public class AddOrder implements UseCase<AddOrder.RequestValues, AddOrder.ResponseValues> {


    @Override
    public Observable<ResponseValues> execute(RequestValues requestValue) {
        return null;
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
