package com.ntg.seqaya.seqayamvpclean.presentation.ordersummery;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.entity.PaymentMethod;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.presentation.main.CartItemsCountListener;
import com.ntg.seqaya.seqayamvpclean.presentation.main.MainActivity;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryOrderFragment extends BaseFragment {

    @BindView(R.id.deliveryLocation)
    TextView deliveryLocation;
    @BindView(R.id.paymentDetails)
    TextView paymentDetails;
    @BindView(R.id.deliveryTime)
    TextView deliveryTime;
    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;
    OrderItemsAdapter orderItemsAdapter;
    @BindView(R.id.cardView)
    LinearLayout cardView;
    @BindView(R.id.cardView2)
    LinearLayout cardView2;
    @BindView(R.id.cardView3)
    LinearLayout cardView3;
    Order order;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.done)
    Button done;
    CartItemsCountListener countListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_main, container, false);
        ButterKnife.bind(this, view);
        ViewUtil.setupActionBarWithBackButton(getActivity(), getString(R.string.order_summary));

        order = (Order) getArguments().getSerializable(MainActivity.ORDER);
        if (order != null) {
            deliveryLocation.setText(order.getLocation().getAddress());
            switch (order.getPaymentMethod()) {
                case PaymentMethod.BANK_TRANSFER:
                    paymentDetails.setText(getString(R.string.bank_transfer));
                    break;

                case PaymentMethod.CREDIT_CARD:
                    paymentDetails.setText(getString(R.string.credit_card));
                    break;
                case PaymentMethod.SADAD:
                    paymentDetails.setText(getString(R.string.sadad));
                    break;
            }
            Log.i("Order", order.toString());
            deliveryTime.setText(order.getDeliveryTime() + " , " + order.getDeliveryDate());
            total.setText(String.valueOf(order.getTotal()));
            setupOrderItemsList();
        }

        ViewUtil.addShadowToView(getActivity(), cardView);
        ViewUtil.addShadowToView(getActivity(), cardView2);
        ViewUtil.addShadowToView(getActivity(), cardView3);

        return view;
    }

    private void setupOrderItemsList() {
        orderItemsAdapter = new OrderItemsAdapter(User.getShoppingCart().getCartItemList(),
                getActivity());
        recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerList.setAdapter(orderItemsAdapter);
        orderItemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            countListener = (CartItemsCountListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @OnClick(R.id.done)
    public void onViewClicked() {
        User.getShoppingCart().getCartItemList().clear();
        countListener.onCartItemsCountChanged(User.getShoppingCart().getCartItemList().size());
        for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); ++i) {
            getFragmentManager().popBackStack();
        }
    }
}
