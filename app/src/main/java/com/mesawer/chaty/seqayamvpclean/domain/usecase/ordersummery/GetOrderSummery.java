package com.mesawer.chaty.seqayamvpclean.domain.usecase.ordersummery;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

import io.reactivex.Observable;

public class GetOrderSummery implements UseCase<GetOrderSummery.RequestValues, GetOrderSummery.ResponseValues> {


    @Override
    public Observable<ResponseValues> execute(RequestValues requestValue) {
        return null;
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
