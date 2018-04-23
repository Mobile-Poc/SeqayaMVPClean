package com.ntg.seqaya.seqayamvpclean.presentation.filter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntg.seqaya.seqayamvpclean.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottlesSizeFragment extends Fragment {
    SheetAdapter sheetAdapter;
    @BindView(R.id.rv)
    RecyclerView rv;

    public BottlesSizeFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<String> list=new ArrayList<>();
        list.add("0.5");
        list.add("1");
        list.add("2");
        list.add("0.75");
        list.add("4");
        list.add("5");

        View view = inflater.inflate(R.layout.fragment_bottles_size, container, false);
        ButterKnife.bind(this, view);
        sheetAdapter=new SheetAdapter(getActivity(),list,"Size");
        rv.setAdapter(sheetAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

}
