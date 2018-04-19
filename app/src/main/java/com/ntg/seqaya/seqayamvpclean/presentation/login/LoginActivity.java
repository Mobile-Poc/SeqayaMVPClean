package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntg.seqaya.seqayamvpclean.BuildConfig;
import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseActivity;
import com.ntg.seqaya.seqayamvpclean.presentation.main.MainActivity;
import com.ntg.seqaya.seqayamvpclean.presentation.registration.RegistrationActivity;
import com.ntg.seqaya.seqayamvpclean.utils.Injection;
import com.ntg.seqaya.seqayamvpclean.utils.LocalManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.isNullOrEmpty;
import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.isValidEmailAddress;
import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.notNullOrEmpty;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.login_email_edit_text)
    EditText loginEmailEditText;
    @BindView(R.id.login_password_edit_text)
    EditText loginPasswordEditText;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.reg_nav_button)
    Button regNavButton;
    @BindView(R.id.facebook_button)
    ImageButton facebookButton;
    @BindView(R.id.twitter_button)
    ImageButton twitterButton;
    @BindView(R.id.google_button)
    ImageButton googleButton;
    @BindView(R.id.login_layout)
    ConstraintLayout loginLayout;
    @BindView(R.id.language_change)
    ImageView languageChange;
    @BindView(R.id.language_letter_indicator)
    TextView languageLetterIndicator;
    private LoginContract.Presenter loginPresenter;
    private ISocialMedia socialMedia;
    private ProgressDialog progressDialog;
    private String language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        socialMedia = SocialMedia.getInstance();
        socialMedia.onCreate(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        super.layout = loginLayout;

        showLoadingIndicator();
        loginPresenter = new LoginPresenter(Injection.provideUseCaseHandler(),
                this, Injection.provideEmailPasswordLogin());
        if (BuildConfig.DEBUG) {
            loginEmailEditText.setText("seqaya@ntgclarity.com");
            loginPasswordEditText.setText("1234");
        }
        SharedPreferences sharedPref = this.getSharedPreferences("lang", 0);
        language = sharedPref.getString("lang", "ar");

        if (language.equals("ar")){
            languageLetterIndicator.setText(R.string.arabic);
        }else {
            languageLetterIndicator.setText(R.string.english);
        }
    }

    private void showLoadingIndicator() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading");
    }

    @Override
    protected void onResume() {
        super.onResume();
        socialMedia.setCallbacks(email -> loginPresenter.emailPasswordLogin(email, ""),
                this::showErrorMessage);
    }

    @Override
    protected void onPause() {
        super.onPause();

        socialMedia.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        socialMedia.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.login_button, R.id.reg_nav_button, R.id.facebook_button, R.id.twitter_button,
            R.id.google_button, R.id.language_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.reg_nav_button:
                navigateToRegistrationActivity();
                break;
            case R.id.facebook_button:
                socialMedia.loginWithFacebook();
                break;
            case R.id.twitter_button:
                socialMedia.loginWithTwitter();
                break;
            case R.id.google_button:
                socialMedia.loginWithGoogle();
                break;
            case R.id.language_change:
                if (languageLetterIndicator.getText().toString()
                        .equals(getString(R.string.english))) {
                    progressDialog.show();
                    languageLetterIndicator.setText(R.string.arabic);
                    SharedPreferences sharedPreferences = this.getSharedPreferences("lang", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("lang", "ar");
                    editor.apply();
                    LocalManager.setLocale(this , "ar");
                    recreate();
                    progressDialog.dismiss();
                } else if (languageLetterIndicator.getText().toString()
                        .equals(getString(R.string.arabic))){
                    progressDialog.show();
                    languageLetterIndicator.setText(R.string.english);
                    SharedPreferences sharedPreferences = this.getSharedPreferences("lang", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("lang", "en");
                    editor.apply();
                    LocalManager.setLocale(this , "en");
                    recreate();
                    progressDialog.dismiss();
                }
                break;
        }
    }

    private void login() {
        String email = loginEmailEditText.getText().toString();
        String password = loginPasswordEditText.getText().toString();
        if (isValidEmailAddress(email) && notNullOrEmpty(password))
            loginPresenter.emailPasswordLogin(email, password);
        if (!isValidEmailAddress(email))
            loginEmailEditText.setError("Invalid email");
        if (isNullOrEmpty(password))
            loginPasswordEditText.setError("Password blank");
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToRegistrationActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalManager.onAttach(base , "ar"));
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        loginPresenter = presenter;
    }
}
