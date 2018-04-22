package com.ntg.seqaya.seqayamvpclean.presentation.tickets;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntg.seqaya.seqayamvpclean.R;

public class TicketFragment extends Fragment {


    public static TicketFragment newInstance() {

        return new TicketFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_ticket, container, false);

        return view;
    }

}
