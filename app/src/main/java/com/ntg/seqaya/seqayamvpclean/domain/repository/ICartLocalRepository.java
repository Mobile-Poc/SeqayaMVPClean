package com.ntg.seqaya.seqayamvpclean.domain.repository;

import com.ntg.seqaya.seqayamvpclean.domain.entity.CartItem;
import com.ntg.seqaya.seqayamvpclean.domain.entity.ShoppingCart;

import java.util.List;


public interface ICartLocalRepository {
    void addToCart(List<CartItem>cartItems , CartItem cartItem , SuccessCallback<List<CartItem>> successCallback);

    void deleteFromCart(CartItem cartItem , SuccessCallback<List<ShoppingCart>> successCallback );
}
