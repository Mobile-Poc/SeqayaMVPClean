package com.mesawer.chaty.seqayamvpclean.domain.usecase.savedlocation;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

import io.reactivex.Observable;

public class GetSavedLocations implements
        UseCase<GetSavedLocations.RequestValues, GetSavedLocations.ResponseValues> {


    @Override
    public Observable<ResponseValues> execute(RequestValues requestValue) {
        return null;
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
