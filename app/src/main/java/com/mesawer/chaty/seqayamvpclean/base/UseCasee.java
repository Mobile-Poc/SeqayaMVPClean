package com.mesawer.chaty.seqayamvpclean.base;

public interface UseCasee<Q extends UseCase.RequestValues, R extends UseCase.ResponseValues> {

    void execute(Q requestValue,
                 UseCaseSuccessCallback<R> successCallback,
                 UseCaseErrorCallback errorCallback);

    interface RequestValues{}

    interface ResponseValues{}

    interface UseCaseSuccessCallback<R> {
        void onSuccess(R response);
    }

    interface UseCaseErrorCallback {
        void onError(String errMsg);
    }
}
