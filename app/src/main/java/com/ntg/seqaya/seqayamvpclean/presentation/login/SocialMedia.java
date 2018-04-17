package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import org.json.JSONException;

import java.util.Collections;

public class SocialMedia implements ISocialMedia {

    private static SocialMedia INSTANCE;
    private static CallbackManager callbackManager;
    private LoginManager loginManager;
    private static final String TAG = SocialMedia.class.getSimpleName();

    private SocialMedia() {
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
    }

    public static SocialMedia getInstance() {
        if (INSTANCE == null) INSTANCE = new SocialMedia();
        return INSTANCE;
    }

    @Override
    public void loginWithFacebook(AppCompatActivity activity,
                                  SuccessCallback<String> successCallback,
                                  ErrorCallback errorCallback) {
        loginManager.logInWithReadPermissions(activity, Collections.singletonList("email"));
        loginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, loginResult.toString());
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                (object, response) -> {
                                    try {
                                        String email = object.getString("email");
                                        successCallback.onSuccess(email);
                                        Log.d(TAG, email);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "email");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {
//                        errorCallback.onError(exception.getMessage());
                        Log.d(TAG, exception.toString());
                    }
                });
    }

    static CallbackManager getCallbackManager() {
        return callbackManager;
    }
}
