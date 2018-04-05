package com.mesawer.chaty.seqayamvpclean.base;

import android.view.View;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showErrorMessage(View layout, String errMsg);
}
