package com.mesawer.chaty.seqayamvpclean.presentation.ordersummery;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mesawer.chaty.seqayamvpclean.R;
import com.mesawer.chaty.seqayamvpclean.base.BaseFragment;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.PaymentMethod;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;
import com.mesawer.chaty.seqayamvpclean.presentation.main.CartItemsCountListener;
import com.mesawer.chaty.seqayamvpclean.presentation.main.MainActivity;
import com.mesawer.chaty.seqayamvpclean.utils.Injection;
import com.mesawer.chaty.seqayamvpclean.utils.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryOrderFragment extends BaseFragment implements OrderSummeryContract.View {

    @BindView(R.id.deliveryLocation)
    TextView deliveryLocation;
    @BindView(R.id.paymentDetails)
    TextView paymentDetails;
    @BindView(R.id.deliveryTime)
    TextView deliveryTime;
    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;
    ListAdapter listAdapter;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.order_summary);

        order = (Order) getArguments().getSerializable(MainActivity.ORDER);
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
        // deliveryLocation.setText(order.getLocation().getAddress());
        // deliveryTime.setText(order.getDeliveryTime() + " , " + order.getDeliveryDate());
        // total.setText(String.valueOf(order.getTotal()));
        listAdapter = new ListAdapter(order.getCartItems(), getActivity());
        recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerList.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        ViewUtil.addShadowToView(getActivity(), cardView);
        ViewUtil.addShadowToView(getActivity(), cardView2);
        ViewUtil.addShadowToView(getActivity(), cardView3);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            countListener = (CartItemsCountListener) context;
        } catch (ClassCastException e) {
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

    @Override
    public void setPresenter(OrderSummeryContract.Presenter presenter) {

    }

    @Override
    public void navigateToMainActivity() {

    }
}