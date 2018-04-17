package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.content.Intent;
import android.content.res.Configuration;
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

import java.util.Locale;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SocialMedia.initializeTwitter(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        super.layout = loginLayout;
        loginPresenter = new LoginPresenter(Injection.provideUseCaseHandler(),
                this, Injection.provideEmailPasswordLogin());
        if (BuildConfig.DEBUG) {
            loginEmailEditText.setText("seqaya@ntgclarity.com");
            loginPasswordEditText.setText("1234");
        }
        changeLocalLanguageToArabic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        socialMedia = SocialMedia.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        socialMedia.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void changeLocalLanguageToArabic() {
        String languageToLoad = "ar";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
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
                socialMedia.loginWithFacebook(this,
                        email -> loginPresenter.emailPasswordLogin(email, ""),
                        this::showErrorMessage);
                break;
            case R.id.twitter_button:
                socialMedia.loginWithTwitter(this,
                        email -> loginPresenter.emailPasswordLogin(email, ""),
                        this::showErrorMessage);
                break;
            case R.id.google_button:
                break;
            case R.id.language_change:
                if (languageLetterIndicator.getText().toString()
                        .equals(getString(R.string.english))) {
                    languageLetterIndicator.setText(R.string.arabic);
                } else {
                    languageLetterIndicator.setText(R.string.english);
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
    public void setPresenter(LoginContract.Presenter presenter) {
        loginPresenter = presenter;
    }
}
