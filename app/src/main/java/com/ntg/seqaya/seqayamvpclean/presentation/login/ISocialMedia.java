package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

public interface ISocialMedia {

    void setCallbacks(SuccessCallback<String> successCallback, ErrorCallback errorCallback);

    void loginWithFacebook();

    void loginWithTwitter();

    void loginWithGoogle();

    void onCreate(AppCompatActivity activity);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onPause();
}
