package com.ntg.seqaya.seqayamvpclean.base;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.utils.LocalManager;

/**
 * Created by ilias on 06/03/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public View layout;

    public void showErrorMessage(String errMsg) {
        if (layout != null) {
            Snackbar snackbar = Snackbar.make(layout, errMsg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(getResources().getColor(R.color.white));
            sbView.setBackgroundColor(
                    ContextCompat.getColor(this, android.R.color.holo_red_dark));
            snackbar.show();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalManager.onAttach(base , "ar"));
    }
}
