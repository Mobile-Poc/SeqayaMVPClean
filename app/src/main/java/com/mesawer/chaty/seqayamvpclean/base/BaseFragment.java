package com.mesawer.chaty.seqayamvpclean.base;

import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.mesawer.chaty.seqayamvpclean.R;

/**
 * Created by ilias on 08/03/2018.
 */

public class BaseFragment extends Fragment {

    public View layout;

    public void showErrorMessage(String errMsg) {
        if (layout != null) {
            Snackbar snackbar = Snackbar.make(layout, errMsg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(getResources().getColor(R.color.white));
            sbView.setBackgroundColor(
                    ContextCompat.getColor(getActivity(), android.R.color.holo_red_dark));
            snackbar.show();
        }
    }
}
