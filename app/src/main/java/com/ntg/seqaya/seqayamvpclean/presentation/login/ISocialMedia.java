package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

public interface ISocialMedia {

    void loginWithFacebook(AppCompatActivity activity,
                           SuccessCallback<String> successCallback,
                           ErrorCallback errorCallback);

    void loginWithTwitter(AppCompatActivity activity,
                          SuccessCallback<String> successCallback,
                          ErrorCallback errorCallback);

    void loginWithGoogle(AppCompatActivity activity,
                         SuccessCallback<String> successCallback,
                         ErrorCallback errorCallback);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onCreate();

    void onPause(AppCompatActivity activity);

    void onStop();
}
