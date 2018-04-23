package com.ntg.seqaya.seqayamvpclean.presentation.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseActivity;
import com.ntg.seqaya.seqayamvpclean.utils.LocalManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.language_change)
    ImageView languageChange;
    @BindView(R.id.login_container)
    FrameLayout loginContainer;
    @BindView(R.id.language_letter_indicator)
    TextView languageLetterIndicator;
    @BindView(R.id.login)
    FrameLayout login;
    private String language;
    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        login.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        loginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_container, loginFragment)
                .commit();
        SharedPreferences sharedPref = getSharedPreferences("lang", 0);
        language = sharedPref.getString("lang", "ar");

        if (language.equals("ar")) {
            languageLetterIndicator.setText(R.string.arabic);
        } else {
            languageLetterIndicator.setText(R.string.english);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.language_change)
    public void onViewClicked() {
        if (languageLetterIndicator.getText().toString()
                .equals(getString(R.string.english))) {
            languageLetterIndicator.setText(R.string.arabic);
            SharedPreferences sharedPreferences = this.getSharedPreferences("lang", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("lang", "ar");
            editor.apply();
            LocalManager.setLocale(this, "ar");
            recreate();
        } else if (languageLetterIndicator.getText().toString()
                .equals(getString(R.string.arabic))) {
            languageLetterIndicator.setText(R.string.english);
            SharedPreferences sharedPreferences = this.getSharedPreferences("lang", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("lang", "en");
            editor.apply();
            LocalManager.setLocale(this, "en");
            recreate();
        }
    }
}
