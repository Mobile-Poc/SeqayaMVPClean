package com.ntg.seqaya.seqayamvpclean.data.datasource;

import com.ntg.seqaya.seqayamvpclean.domain.entity.CartItem;
import com.ntg.seqaya.seqayamvpclean.domain.entity.ShoppingCart;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

/**
 * Created by islam on 4/23/2018.
 */

public interface CartLocalDataSource {
    void addToCart(List<CartItem>cartItems , CartItem cartItem , SuccessCallback<List<CartItem>> successCallback);

    void deleteFromCart(CartItem cartItem , SuccessCallback<List<ShoppingCart>> successCallback );

}
