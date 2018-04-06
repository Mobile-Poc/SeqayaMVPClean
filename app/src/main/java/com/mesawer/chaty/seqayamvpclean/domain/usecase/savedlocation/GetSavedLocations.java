package com.mesawer.chaty.seqayamvpclean.domain.usecase.savedlocation;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class GetSavedLocations implements
        UseCase<GetSavedLocations.RequestValues, GetSavedLocations.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseCallback<ResponseValues> useCaseCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
