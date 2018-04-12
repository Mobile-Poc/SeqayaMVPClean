package com.mesawer.chaty.seqayamvpclean.presentation.paymentmethod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mesawer.chaty.seqayamvpclean.R;
import com.mesawer.chaty.seqayamvpclean.base.BaseFragment;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Order;
import com.mesawer.chaty.seqayamvpclean.domain.entity.PaymentMethod;
import com.mesawer.chaty.seqayamvpclean.presentation.main.MainActivity;
import com.mesawer.chaty.seqayamvpclean.utils.Injection;
import com.mesawer.chaty.seqayamvpclean.utils.ViewUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentMethodFragment extends BaseFragment implements PaymentMethodContract.View, View.OnClickListener {
    View view;
    RelativeLayout bankTransfer, sadad, creditCard;
    Order order;
    PaymentMethodContract.Presenter paymentpresenter;
    FrameLayout PaymentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_payment, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.payment_method));
        bankTransfer = view.findViewById(R.id.bankTransfer);
        sadad = view.findViewById(R.id.sadad);
        creditCard = view.findViewById(R.id.creditCard);
        PaymentLayout = view.findViewById(R.id.payment_layout);
        ViewUtil.addShadowToView(getActivity(), bankTransfer);
        ViewUtil.addShadowToView(getActivity(), sadad);
        ViewUtil.addShadowToView(getActivity(), creditCard);
        this.layout = PaymentLayout;
        paymentpresenter = new PaymentMethodPresenter(this, Injection.provideAddOrder());
        Bundle bundle = getArguments();
        order = (Order) bundle.getSerializable(MainActivity.ORDER);

        return view;
    }

    @Override
    public void setPresenter(PaymentMethodContract.Presenter presenter) {

    }

    @Override
    public void navigateToOrderSummeryFragment(Order order) {
//        Bundle args = new Bundle();
//        args.putSerializable(MainActivity.ORDER, order);
//        SummaryOrderFragment summaryOrderFragment = new SummaryOrderFragment();
//        summaryOrderFragment.setArguments(args);
//        getActivity().getFragmentManager().beginTransaction()
//                .addToBackStack(null).replace(R.id.container, summaryOrderFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (view.getId()) {
            case R.id.bankTransfer:
                order.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
                paymentpresenter.addOrder(order);
                break;
            case R.id.sadad:
                order.setPaymentMethod(PaymentMethod.SADAD);
                paymentpresenter.addOrder(order);
                break;
            case R.id.creditCard:
                order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
                paymentpresenter.addOrder(order);
                break;

        }
    }
}
