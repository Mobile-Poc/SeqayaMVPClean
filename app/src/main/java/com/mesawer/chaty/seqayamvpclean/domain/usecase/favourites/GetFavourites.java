package com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class GetFavourites implements UseCase<GetFavourites.RequestValues, GetFavourites.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseCallback<ResponseValues> useCaseCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
