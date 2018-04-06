package com.mesawer.chaty.seqayamvpclean.domain.usecase.location;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class AddLocation implements UseCase<AddLocation.RequestValues, AddLocation.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseCallback<ResponseValues> useCaseCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
