package com.mesawer.chaty.seqayamvpclean.presentation.orderhistory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mesawer.chaty.seqayamvpclean.R;
import com.mesawer.chaty.seqayamvpclean.base.BaseFragment;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.utils.Injection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends BaseFragment implements OrderHistoryContract.View {

    @BindView(R.id.loading_indicator)
    LinearLayout loadingIndicator;
    Unbinder unbinder;
    @BindView(R.id.order_history_rv)
    RecyclerView orderHistoryRv;
    @BindView(R.id.order_history_layout)
    FrameLayout orderHistoryLayout;
    @BindView(R.id.no_items_layout)
    LinearLayout noItemsLayout;
    private OrderHistoryAdapter adapter;
    private LinearLayoutManager layoutManager;
    OrderHistoryContract.Presenter orderHistoryPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        super.layout = orderHistoryLayout;
        orderHistoryPresenter = new OrderHistoryPresenter(this,
                Injection.provideGetOrderHistory());
        setupActionBar();
        layoutManager = new LinearLayoutManager(this.getActivity());
        orderHistoryRv.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        orderHistoryPresenter.getOrderHistory();
    }

    private void setupActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.history));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showOrderHistory(List<Order> orders) {
        if (loadingIndicator != null)
            loadingIndicator.setVisibility(View.GONE);
        if (orders.isEmpty()) {
            noItemsLayout.setVisibility(View.VISIBLE);
        } else {
            adapter = new OrderHistoryAdapter(orders, getActivity());
            orderHistoryRv.setAdapter(adapter);
        }
    }

    @Override
    public void setPresenter(OrderHistoryContract.Presenter presenter) {
        orderHistoryPresenter = presenter;
    }
}
