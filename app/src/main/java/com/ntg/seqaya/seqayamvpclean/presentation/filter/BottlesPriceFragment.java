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
public class BottlesPriceFragment extends Fragment {
    SheetAdapter sheetAdapter;
    @BindView(R.id.rv)
    RecyclerView rv;

    public BottlesPriceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<String> list=new ArrayList<>();
        list.add("30");
        list.add("40");
        list.add("50");
        list.add("60");
        list.add("70");
        list.add("80");
        View view = inflater.inflate(R.layout.fragment_bottles_price, container, false);
        ButterKnife.bind(this, view);
        sheetAdapter=new SheetAdapter(getActivity(),list);
        rv.setAdapter(sheetAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

}
