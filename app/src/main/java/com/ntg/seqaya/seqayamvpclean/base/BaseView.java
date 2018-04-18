package com.ntg.seqaya.seqayamvpclean.base;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showErrorMessage(String errMsg);
}
