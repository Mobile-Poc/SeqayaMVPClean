package com.mesawer.chaty.seqayamvpclean.domain.usecase.orderhistory;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class GetOrderHistory implements UseCase<GetOrderHistory.RequestValues, GetOrderHistory.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseCallback<ResponseValues> useCaseCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
