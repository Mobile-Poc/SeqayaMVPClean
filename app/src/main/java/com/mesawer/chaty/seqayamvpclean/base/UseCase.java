package com.mesawer.chaty.seqayamvpclean.base;

public interface UseCase<Q extends UseCase.RequestValues, R extends UseCase.ResponseValues> {

    void execute(Q requestValue, UseCaseCallback<R> useCaseCallback);

    public interface RequestValues{}

    public interface ResponseValues{}

    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError();
    }
}
