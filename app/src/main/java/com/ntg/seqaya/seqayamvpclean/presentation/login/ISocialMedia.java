package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.support.v7.app.AppCompatActivity;

import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

public interface ISocialMedia {

    void loginWithFacebook(AppCompatActivity activity,
                           SuccessCallback<String> successCallback,
                           ErrorCallback errorCallback);
}
