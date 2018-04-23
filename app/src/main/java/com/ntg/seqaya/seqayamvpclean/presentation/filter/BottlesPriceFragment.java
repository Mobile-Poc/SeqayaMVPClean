package com.ntg.seqaya.seqayamvpclean.presentation.filter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ntg.seqaya.seqayamvpclean.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottlesPriceFragment extends Fragment {


    @BindView(R.id.priceFrom)
    TextInputEditText priceFrom;
    @BindView(R.id.priceTo)
    TextInputEditText priceTo;
    TextWatcher watch = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable arg0) {}

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

        @Override
        public void onTextChanged(CharSequence s, int a, int b, int c) {
            if (!priceTo.getText().toString().equals("")) {
                if (Integer.parseInt(priceTo.getText().toString()) > Integer.parseInt(priceFrom.getText().toString())) {
                    FilterLists.priceList.put(0, priceFrom.getText().toString());
                    FilterLists.priceList.put(1, priceTo.getText().toString());
                } else
                    priceTo.setError("Should be larger than from");
            }

        }
    };

    public BottlesPriceFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottles_price, container, false);
        ButterKnife.bind(this, view);
        priceTo.addTextChangedListener(watch);

        return view;
    }

}
