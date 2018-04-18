package com.ntg.seqaya.seqayamvpclean.presentation.orderhistory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.utils.Injection;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

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
        ViewUtil.setupActionBar(getActivity(), getString(R.string.history));
        layoutManager = new LinearLayoutManager(this.getActivity());
        orderHistoryRv.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        orderHistoryPresenter.getOrderHistory();
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
            if (noItemsLayout != null)
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
