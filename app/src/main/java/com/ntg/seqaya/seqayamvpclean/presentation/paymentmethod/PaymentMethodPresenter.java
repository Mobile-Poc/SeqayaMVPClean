package com.ntg.seqaya.seqayamvpclean.presentation.paymentmethod;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.paymentmethod.AddOrder;

public class PaymentMethodPresenter implements PaymentMethodContract.Presenter{

    private PaymentMethodContract.View paymentMethodView;
    private AddOrder addOrder;

    public PaymentMethodPresenter(PaymentMethodContract.View paymentMethodView, AddOrder addOrder) {
        this.paymentMethodView = paymentMethodView;
        this.addOrder = addOrder;
    }

    @Override
    public void addOrder(Order order) {
        AddOrder.RequestValues requestValues=new AddOrder.RequestValues(order);

        addOrder.execute(requestValues, new UseCase.UseCaseSuccessCallback<AddOrder.ResponseValues>() {
                    @Override
                    public void onSuccess(AddOrder.ResponseValues response) {
                        paymentMethodView.navigateToOrderSummeryFragment(response.getOrder());
                    }
                }, new UseCase.UseCaseErrorCallback() {
                    @Override
                    public void onError(String errMsg) {
                        paymentMethodView.showErrorMessage(errMsg);
                    }
                }
                /*response -> paymentMethodView.navigateToOrderSummeryFragment(response.getOrder()),
                paymentMethodView::showErrorMessage*/);
    }
}
