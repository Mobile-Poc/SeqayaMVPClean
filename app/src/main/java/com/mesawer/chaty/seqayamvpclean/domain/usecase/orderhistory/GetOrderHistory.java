package com.mesawer.chaty.seqayamvpclean.domain.usecase.orderhistory;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

import io.reactivex.Observable;

public class GetOrderHistory implements UseCase<GetOrderHistory.RequestValues, GetOrderHistory.ResponseValues> {


    @Override
    public Observable<ResponseValues> execute(RequestValues requestValue) {
        return null;
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
