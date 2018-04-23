package com.ntg.seqaya.seqayamvpclean.presentation.settings;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.presentation.login.LoginActivity;
import com.ntg.seqaya.seqayamvpclean.presentation.tickets.TicketFragment;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment {


    @BindView(R.id.sign_out_layout)
    LinearLayout signOutLayout;
    @BindView(R.id.Tickets)
    LinearLayout TicketLayout;
    Unbinder unbinder;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);

        ViewUtil.setupActionBarWithoutBackButton(getActivity() , getString(R.string.settings));
        ViewUtil.addShadowToView(getActivity(), signOutLayout);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.sign_out_layout)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    @OnClick(R.id.Tickets)
    public void onTicketsClicked(){
        getFragmentManager()
                .beginTransaction().addToBackStack(null)
                .replace(R.id.container, TicketFragment.newInstance()).commit();
    }
}
