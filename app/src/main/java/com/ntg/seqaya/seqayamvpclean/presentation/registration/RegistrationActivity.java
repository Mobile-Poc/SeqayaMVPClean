package com.ntg.seqaya.seqayamvpclean.presentation.registration;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseActivity;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.entity.UserAPI;
import com.ntg.seqaya.seqayamvpclean.presentation.login.LoginActivity;
import com.ntg.seqaya.seqayamvpclean.utils.Injection;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.isNullOrEmpty;
import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.isValidEmailAddress;
import static com.ntg.seqaya.seqayamvpclean.utils.StringUtil.notNullOrEmpty;

public class RegistrationActivity extends BaseActivity implements RegistrationContract.View {

    @BindView(R.id.reg_name_edit_text)
    EditText regNameEditText;
    @BindView(R.id.reg_email_edit_text)
    EditText regEmailEditText;
    @BindView(R.id.reg_phone_edit_text)
    EditText regPhoneEditText;
    @BindView(R.id.reg_password_edit_text)
    EditText regPasswordEditText;
    @BindView(R.id.reg_rePassword_edit_text)
    EditText regRePasswordEditText;
    @BindView(R.id.registration_button)
    Button registrationButton;
    @BindView(R.id.login_nav_button)
    Button loginNavButton;
    @BindView(R.id.registration_layout)
    ConstraintLayout registrationLayout;
    private RegistrationContract.Presenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        super.layout = registrationLayout;
        registrationPresenter = new RegistrationPresenter(this,
                Injection.provideAddNewUser());
        changeLocalLanguageToArabic();

    }

    private void changeLocalLanguageToArabic() {
        String languageToLoad = "ar";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    @OnClick({R.id.registration_button, R.id.login_nav_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.registration_button:
                registrationPresenter.addNewUser(getUser());
                break;
            case R.id.login_nav_button:
                navigateToLoginActivity();
                break;
        }
    }

    private UserAPI getUser() {
        String name = regNameEditText.getText().toString();
        String email = regEmailEditText.getText().toString();
        String password = regPasswordEditText.getText().toString();
        String rePassword = regRePasswordEditText.getText().toString();
        String phone_number = regPhoneEditText.getText().toString();

        if (notNullOrEmpty(name) && isValidEmailAddress(email) && notNullOrEmpty(password) &&
                password.equals(rePassword) && notNullOrEmpty(phone_number)) {
            UserAPI userAPI = new UserAPI();
            userAPI.setEmail(email);
            userAPI.setPassword(password);
            userAPI.setName(name);
            return userAPI;
        }

        // return new User(name, email, password, phone_number);
        if (isNullOrEmpty(name))
            regNameEditText.setError("This field shouldn't be blank");
        if (!isValidEmailAddress(email))
            regEmailEditText.setError("Invalid email address");
        if (isNullOrEmpty(password))
            regPasswordEditText.setError("This field shouldn't be blank");
        if (!password.equals(rePassword))
            regRePasswordEditText.setError("password mismatching");
        if (isNullOrEmpty(phone_number))
            regPhoneEditText.setError("This field shouldn't be blank");
        return null;
    }

    @Override
    public void navigateToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(RegistrationContract.Presenter presenter) {
        registrationPresenter = presenter;
    }
}
