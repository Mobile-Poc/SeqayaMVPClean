package com.ntg.seqaya.seqayamvpclean.presentation.filter;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ntg.seqaya.seqayamvpclean.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {
    //    @BindView(R.id.button)
//    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    Unbinder unbinder;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.bottom_sheet)
    ConstraintLayout bottomSheet;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;


    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        unbinder = ButterKnife.bind(this, view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        fragmentContainer.getLayoutParams().height = height / 3;
        fragmentContainer.requestLayout();
        BottlesSizeFragment fragment2 = new BottlesSizeFragment();
        FragmentManager fragmentManager2 = getChildFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        fragmentTransaction2.replace(R.id.fragment_container, fragment2);
        fragmentTransaction2.commit();
        active(button2);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.button2, R.id.button3, R.id.button4, R.id.button7, R.id.button8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.button:
//                FragmentOne fragment = new FragmentOne();
//                FragmentManager fragmentManager = getChildFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();
//                active(button);
//                rest(button2);
//                rest(button3);
//                rest(button4);
//                break;
            case R.id.button2:
                BottlesSizeFragment fragment2 = new BottlesSizeFragment();
                FragmentManager fragmentManager2 = getChildFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.fragment_container, fragment2);
                fragmentTransaction2.commit();
                active(button2);
                rest(button3);
                rest(button4);
                break;
            case R.id.button3:
                BottlesPriceFragment fragment3 = new BottlesPriceFragment();
                FragmentManager fragmentManager3 = getChildFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.fragment_container, fragment3);
                fragmentTransaction3.commit();
                // button3.setPaintFlags(button.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
                active(button3);
                rest(button2);
                rest(button4);
                break;
            case R.id.button4:
                BottlesNumFragment fragment4 = new BottlesNumFragment();
                FragmentManager fragmentManager4 = getChildFragmentManager();
                FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                fragmentTransaction4.replace(R.id.fragment_container, fragment4);
                fragmentTransaction4.commit();
                active(button4);
                rest(button2);
                rest(button3);
                // button4.setPaintFlags(button.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
                break;
            case R.id.button7:
                this.dismiss();
                break;
            case R.id.button8:
                // button8.setPaintFlags(button.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
                this.dismiss();
                break;
        }
    }

    public void rest(Button button) {
        button.setBackground(this.getResources().getDrawable(R.drawable.custom_btn_background));
        button.setTextColor(this.getResources().getColor(R.color.black));
        button.setTypeface(null, Typeface.NORMAL);
    }

    public void active(Button button) {

        button.setBackground(this.getResources().getDrawable(R.drawable.btn_background));
        button.setTextColor(this.getResources().getColor(R.color.white));
        button.setTypeface(null, Typeface.BOLD);
    }

}
