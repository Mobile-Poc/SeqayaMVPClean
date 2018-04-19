package com.ntg.seqaya.seqayamvpclean.presentation.paymentmethod;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.presentation.main.MainActivity;
import com.ntg.seqaya.seqayamvpclean.presentation.ordersummery.SummaryOrderFragment;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CashOnDeliveryFragment extends BaseFragment implements View.OnClickListener {
    View view;
    Order order;
    TextView deliveryLocation, phone_number;
    TextView deliveryLocationText, customer_number;
    Button confim;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cash_on_delivery, container, false);
        ViewUtil.setupActionBar(getActivity(), getString(R.string.cash_on_delivery));
        deliveryLocation = view.findViewById(R.id.deliveryLocation);
        phone_number = view.findViewById(R.id.phone_number);
        confim = view.findViewById(R.id.confim);
        deliveryLocationText = view.findViewById(R.id.deliveryLocationText);
        customer_number = view.findViewById(R.id.customer_number);

        order = (Order) getArguments().getSerializable(MainActivity.ORDER);
        deliveryLocation.setText(order.getLocation().getAddress());
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        confim.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confim:
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
