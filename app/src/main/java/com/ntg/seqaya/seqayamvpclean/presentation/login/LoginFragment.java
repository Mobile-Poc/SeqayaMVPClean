package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntg.seqaya.seqayamvpclean.BuildConfig;
import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.presentation.main.MainActivity;
import com.ntg.seqaya.seqayamvpclean.presentation.registration.RegistrationActivity;
import com.ntg.seqaya.seqayamvpclean.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.isNullOrEmpty;
import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.isValidEmailAddress;
import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.notNullOrEmpty;

public class LoginFragment extends BaseFragment implements LoginContract.View {

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
    @BindView(R.id.forget_password_text_view)
    TextView forgetPasswordTextView;
    private LoginContract.Presenter loginPresenter;
    private ISocialMedia socialMedia;
    private ForgetPasswordFragment forgetPasswordFragment;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        socialMedia = new SocialMedia();
        socialMedia.onCreate(getActivity());
        super.layout = loginLayout;
        loginLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        forgetPasswordFragment = new ForgetPasswordFragment();
        loginPresenter = new LoginPresenter(Injection.provideUseCaseHandler(),
                this, Injection.provideEmailPasswordLogin());
        if (BuildConfig.DEBUG) {
            loginEmailEditText.setText("seqaya@ntgclarity.com");
            loginPasswordEditText.setText("1234");
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        socialMedia.setCallbacks(email -> loginPresenter.emailPasswordLogin(email, ""),
                this::showErrorMessage);
    }

    @Override
    public void onPause() {
        super.onPause();

        socialMedia.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        socialMedia.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.login_button, R.id.reg_nav_button, R.id.facebook_button, R.id.twitter_button,
            R.id.google_button, R.id.forget_password_text_view})
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
            case R.id.forget_password_text_view:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.login_container, forgetPasswordFragment)
                        .commit();
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
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void navigateToRegistrationActivity() {
        Intent intent = new Intent(getActivity(), RegistrationActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        loginPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
