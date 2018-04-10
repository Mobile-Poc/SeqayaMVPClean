package com.mesawer.chaty.seqayamvpclean.domain.repository;

public interface SuccessCallback<T> {
    void onSuccess(T result);
}
