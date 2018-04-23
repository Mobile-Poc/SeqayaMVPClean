package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import org.json.JSONException;

import java.util.Collections;

public class SocialMedia implements ISocialMedia {

    private FragmentActivity activity;
    private SuccessCallback<String> successCallback;
    private ErrorCallback errorCallback;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 007;
    private static CallbackManager callbackManager;
    private LoginManager loginManager;
    private TwitterAuthClient authClient;
    private GoogleSignInAccount account;
    private static final String TAG = SocialMedia.class.getSimpleName();

    public SocialMedia() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (callbackManager != null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
        if (authClient != null)
            authClient.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onCreate(FragmentActivity activity) {
        this.activity = activity;
        Twitter.initialize(activity);
    }

    @Override
    public void onPause() {
//        if (loginManager != null)
//            loginManager.unregisterCallback(callbackManager);
        if (googleApiClient != null) {
            googleApiClient.stopAutoManage(activity);
            googleApiClient.disconnect();
        }
    }

    @Override
    public void setCallbacks(SuccessCallback<String> successCallback, ErrorCallback errorCallback) {
        this.successCallback = successCallback;
        this.errorCallback = errorCallback;
    }

    //region Facebook
    @Override
    public void loginWithFacebook() {
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
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
    //endregion

    //region Twitter
    @Override
    public void loginWithTwitter() {
        authClient = new TwitterAuthClient();
        authClient.authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        String email = result.data;
                        successCallback.onSuccess(email);
                        Log.d(TAG, email);
                    }

                    @Override
                    public void failure(TwitterException exception) {
//                        errorCallback.onError(exception.getMessage());
                        Log.d(TAG, exception.getMessage());
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, exception.getMessage());
            }
        });
    }
    //endregion

    //region Google
    @Override
    public void loginWithGoogle() {
        initializeGoogleClient(activity);
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        activity.startActivityForResult(intent, RC_SIGN_IN);
    }

    private void initializeGoogleClient(FragmentActivity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess() + " " +
                result.getStatus().toString() + " " + result.getStatus().getStatusMessage());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            account = result.getSignInAccount();

            Log.d(TAG, "display name: " + account.getDisplayName());

            String personName = account.getDisplayName();
            String email = account.getEmail();
            successCallback.onSuccess(email);

            Log.d(TAG, "Name: " + personName + ", email: " + email);
        }
    }

    private GoogleApiClient.OnConnectionFailedListener failedListener = connectionResult -> {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        errorCallback.onError(connectionResult.getErrorMessage());
    };
    //endregion
}

