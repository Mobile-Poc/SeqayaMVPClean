package com.mesawer.chaty.seqayamvpclean.base;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showErrorMessage(String errMsg);
}
