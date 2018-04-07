package com.mesawer.chaty.seqayamvpclean.domain.usecase.savedlocation;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class GetSavedLocations implements
        UseCase<GetSavedLocations.RequestValues, GetSavedLocations.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseSuccessCallback<ResponseValues> successCallback, UseCaseErrorCallback errorCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
