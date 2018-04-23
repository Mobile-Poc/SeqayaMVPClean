package com.ntg.seqaya.seqayamvpclean.presentation.filter;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ntg.seqaya.seqayamvpclean.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {

    @BindView(R.id.bottlesSizeBtn)
    Button bottlesSizeBtn;
    @BindView(R.id.bottlesPriceBtn)
    Button bottlesPriceBtn;
    @BindView(R.id.bottlesNumBtn)
    Button bottlesNumBtn;
    Unbinder unbinder;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.done)
    Button done;
    @BindView(R.id.fragment_container)
    ViewPager fragmentContainer;
    int p = 0;
    private FilterItemsListener filterItemsListener;

    public BottomSheetFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            filterItemsListener = (FilterItemsListener) args.getSerializable("filterItems");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        unbinder = ButterKnife.bind(this, view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        fragmentContainer.getLayoutParams().height = height / 3;
        fragmentContainer.requestLayout();
        Pager mAdapter = new Pager(getChildFragmentManager(), 3);
        fragmentContainer.setOffscreenPageLimit(3);
        fragmentContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (p) {
                    case 0: {
                        active(bottlesSizeBtn);
                        rest(bottlesPriceBtn);
                        rest(bottlesNumBtn);
                        break;
                    }
                    case 1: {
                        active(bottlesPriceBtn);
                        rest(bottlesSizeBtn);
                        rest(bottlesNumBtn);
                        break;
                    }
                    case 2: {
                        active(bottlesNumBtn);
                        rest(bottlesSizeBtn);
                        rest(bottlesPriceBtn);
                        break;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                p = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
        fragmentContainer.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bottlesSizeBtn, R.id.bottlesPriceBtn, R.id.bottlesNumBtn, R.id.cancel, R.id.done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottlesSizeBtn:
                p = 0;
                fragmentContainer.setCurrentItem(p);
                break;
            case R.id.bottlesPriceBtn:
                p = 1;
                fragmentContainer.setCurrentItem(p);
                break;
            case R.id.bottlesNumBtn:
                p = 2;
                fragmentContainer.setCurrentItem(p);
                break;
            case R.id.cancel:
                this.dismiss();
                break;
            case R.id.done:
                List<String> items = new ArrayList<>();
                items.addAll(FilterLists.numList.values());
                items.addAll(FilterLists.priceList.values());
                items.addAll(FilterLists.sizeList.values());
                FilterLists.numList.clear();
                FilterLists.sizeList.clear();
                FilterLists.priceList.clear();
                filterItemsListener.onFilterItemsSelected(items);
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
