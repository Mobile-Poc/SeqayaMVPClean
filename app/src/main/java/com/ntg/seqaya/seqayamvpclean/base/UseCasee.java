package com.ntg.seqaya.seqayamvpclean.base;

public interface UseCasee<Q extends UseCasee.RequestValues, R extends UseCasee.ResponseValues> {

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
