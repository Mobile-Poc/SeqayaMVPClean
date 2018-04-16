package com.ntg.seqaya.seqayamvpclean.domain.repository;

public interface SuccessCallback<T> {
    void onSuccess(T result);
}
