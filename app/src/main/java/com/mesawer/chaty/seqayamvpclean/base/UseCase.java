package com.mesawer.chaty.seqayamvpclean.base;

import io.reactivex.Observable;

public interface UseCase<Q extends UseCase.RequestValues, R extends UseCase.ResponseValues> {
    Observable<R> execute(Q requestValue);

    public interface RequestValues{}

    public interface ResponseValues{}
}
