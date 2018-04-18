package com.ntg.seqaya.seqayamvpclean.presentation.filter;


import android.os.Bundle;
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

    public BottlesSizeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<String> list=new ArrayList<>();
        list.add("0.5 لتر");
        list.add(" لتر");
        list.add("2 لتر");
        list.add("3 لتر");
        list.add("4 لتر");
        list.add("5 لتر");
        View view = inflater.inflate(R.layout.fragment_bottles_size, container, false);
        ButterKnife.bind(this, view);
        sheetAdapter=new SheetAdapter(getActivity(),list);
        rv.setAdapter(sheetAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

}
