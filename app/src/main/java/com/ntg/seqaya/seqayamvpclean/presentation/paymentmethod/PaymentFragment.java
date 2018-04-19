package com.ntg.seqaya.seqayamvpclean.presentation.paymentmethod;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.entity.PaymentMethod;
import com.ntg.seqaya.seqayamvpclean.presentation.main.MainActivity;
import com.ntg.seqaya.seqayamvpclean.presentation.ordersummery.SummaryOrderFragment;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends BaseFragment implements View.OnClickListener {

    RelativeLayout bankTransfer, sadad, creditCard;
    Order order;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        ViewUtil.setupActionBar(getActivity(), getString(R.string.payment_method));
        bankTransfer = view.findViewById(R.id.bankTransfer);
        sadad = view.findViewById(R.id.sadad);
        creditCard = view.findViewById(R.id.creditCard);
        ViewUtil.addShadowToView(getActivity(), bankTransfer);
        ViewUtil.addShadowToView(getActivity(), sadad);
        ViewUtil.addShadowToView(getActivity(), creditCard);
        Bundle bundle = getArguments();
        order = (Order) bundle.getSerializable(MainActivity.ORDER);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bankTransfer.setOnClickListener(this);
        sadad.setOnClickListener(this);
        creditCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bankTransfer:
                order.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
                navigateToOrderSummery(order);
                break;
            case R.id.sadad:
                order.setPaymentMethod(PaymentMethod.SADAD);
                navigateToOrderSummery(order);
                break;
            case R.id.creditCard:
                order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
                navigateToOrderSummery(order);
                break;

        }
    }

    public void navigateToOrderSummery(final Order order) {
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        Call<Order> call = productService.addNewOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Bundle args = new Bundle();
                args.putSerializable(MainActivity.ORDER, order);
                SummaryOrderFragment summaryOrderFragment = new SummaryOrderFragment();
                summaryOrderFragment.setArguments(args);
                getActivity().getFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.container, summaryOrderFragment).commit();
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }
}
