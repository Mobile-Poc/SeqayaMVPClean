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
public class BottlesNumFragment extends Fragment {

    SheetAdapter sheetAdapter;
    @BindView(R.id.rv)
    RecyclerView rv;
    public BottlesNumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<String> list=new ArrayList<>();
        list.add("20");
        list.add("40");
        list.add("60");
        list.add("80");
        list.add("100");
        list.add("120");
        View view = inflater.inflate(R.layout.fragment_bottles_num, container, false);
        ButterKnife.bind(this, view);
        sheetAdapter=new SheetAdapter(getActivity(),list);
        rv.setAdapter(sheetAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

}
