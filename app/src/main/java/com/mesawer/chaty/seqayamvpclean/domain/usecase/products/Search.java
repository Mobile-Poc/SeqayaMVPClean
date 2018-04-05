package com.mesawer.chaty.seqayamvpclean.domain.usecase.products;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

import io.reactivex.Observable;

public class Search implements UseCase<Search.RequestValues, Search.ResponseValues> {


    @Override
    public Observable<ResponseValues> execute(RequestValues requestValue) {
        return null;
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{

    }
}
