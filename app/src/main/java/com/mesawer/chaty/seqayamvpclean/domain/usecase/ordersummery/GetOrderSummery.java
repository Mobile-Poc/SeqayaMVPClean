package com.mesawer.chaty.seqayamvpclean.domain.usecase.ordersummery;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class GetOrderSummery implements
        UseCase<GetOrderSummery.RequestValues, GetOrderSummery.ResponseValues> {


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
