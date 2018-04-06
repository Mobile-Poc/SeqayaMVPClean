package com.mesawer.chaty.seqayamvpclean.domain.usecase.paymentmethod;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class AddOrder implements UseCase<AddOrder.RequestValues, AddOrder.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseCallback<ResponseValues> useCaseCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
