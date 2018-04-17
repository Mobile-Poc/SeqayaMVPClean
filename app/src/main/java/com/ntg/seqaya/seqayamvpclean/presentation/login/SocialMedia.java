package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.content.Context;
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
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 007;
    private static SocialMedia INSTANCE;
    private static CallbackManager callbackManager;
    private LoginManager loginManager;
    private TwitterAuthClient authClient;
    private GoogleSignInAccount account;
    private static final String TAG = SocialMedia.class.getSimpleName();

    private SocialMedia() {
    }

    public static SocialMedia getInstance() {
        if (INSTANCE == null) INSTANCE = new SocialMedia();
        return INSTANCE;
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
    public void onCreate() {

    }

    @Override
    public void onPause(AppCompatActivity activity) {
        if (googleApiClient != null) {
            googleApiClient.stopAutoManage(activity);
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onStop() {

    }

    //region Facebook
    @Override
    public void loginWithFacebook(AppCompatActivity activity,
                                  SuccessCallback<String> successCallback,
                                  ErrorCallback errorCallback) {
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
    public void loginWithTwitter(AppCompatActivity activity,
                                 SuccessCallback<String> successCallback,
                                 ErrorCallback errorCallback) {
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


    public static void initializeTwitter(Context context) {
        Twitter.initialize(context);
    }
    //endregion

    //region Google
    @Override
    public void loginWithGoogle(AppCompatActivity activity,
                                SuccessCallback<String> successCallback,
                                ErrorCallback errorCallback) {
        initializeGoogleClient(activity);
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        activity.startActivityForResult(intent, RC_SIGN_IN);
    }

    private void initializeGoogleClient(AppCompatActivity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            account = result.getSignInAccount();

            Log.d(TAG, "display name: " + account.getDisplayName());

            String personName = account.getDisplayName();
//            String personPhotoUrl = account.getPhotoUrl().toString();
            String email = account.getEmail();

            Log.d(TAG, "Name: " + personName + ", email: " + email);

//            txtName.setText(personName);
//            txtEmail.setText(email);
//            Glide.with(getApplicationContext()).load(personPhotoUrl)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imgProfilePic);
//
//            updateUI(true);
//        } else {
//            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

    GoogleApiClient.OnConnectionFailedListener failedListener = connectionResult ->
            Log.d(TAG, "onConnectionFailed:" + connectionResult);
    //endregion

//    private void showProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(this);
//            mProgressDialog.setMessage("Loading");
//            mProgressDialog.setIndeterminate(true);
//        }
//
//        mProgressDialog.show();
//    }
//
//    private void hideProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.hide();
//        }
}

