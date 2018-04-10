package com.mesawer.chaty.seqayamvpclean.domain.usecase.paymentmethod;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class AddOrder implements UseCase<AddOrder.RequestValues, AddOrder.ResponseValues> {


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
