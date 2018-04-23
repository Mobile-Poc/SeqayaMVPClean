package com.ntg.seqaya.seqayamvpclean.presentation.manufacturer;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManufactureFragment extends BaseFragment {

    @BindView(R.id.manufacturer)
    RecyclerView manufacture;
    private ManufactureAdapter manufactureAdapter;
    private List<Integer> manufacture_images;
    private ManufacturerClick manufacturerClick;
    public static ManufactureFragment newInstance() {

        return new ManufactureFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manfucturer, container, false);
        ButterKnife.bind(this , view);
        manufacture_images = new ArrayList<>();

        manufacture_images.add(R.drawable.moya);
        manufacture_images.add(R.drawable.nagd);
        manufacture_images.add(R.drawable.naqe);
        manufacture_images.add(R.drawable.nofa);
        manufacture_images.add(R.drawable.rama);
        manufacture_images.add(R.drawable.qasem);
        manufacture_images.add(R.drawable.nestle);
        manufacture_images.add(R.drawable.naya);

        manufactureAdapter = new ManufactureAdapter(manufacture_images, manufacturerClick,
                getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity() , 3);
        manufacture.setLayoutManager(gridLayoutManager);
        manufacture.setAdapter(manufactureAdapter);

        ViewUtil.setupActionBarWithoutBackButton(getActivity() , getString(R.string.manufacurers));
        return view;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            manufacturerClick = (ManufacturerClick)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement onViewSelected");
        }
    }
}
