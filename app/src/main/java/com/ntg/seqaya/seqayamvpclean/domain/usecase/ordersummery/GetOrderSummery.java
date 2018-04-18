package com.ntg.seqaya.seqayamvpclean.domain.usecase.ordersummery;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;

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
