package com.ntg.seqaya.seqayamvpclean.data.repository;

import com.ntg.seqaya.seqayamvpclean.data.datasource.local.CartLocalDataSource;
import com.ntg.seqaya.seqayamvpclean.domain.entity.CartItem;
import com.ntg.seqaya.seqayamvpclean.domain.entity.ShoppingCart;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ICartLocalRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

/**
 * Created by islam on 4/23/2018.
 */

public class CartLocalRepository implements ICartLocalRepository{

    private static CartLocalRepository INSTANCE;
    private CartLocalDataSource cartLocalDataSource;

    private CartLocalRepository(CartLocalDataSource cartLocalDataSource) {
        this.cartLocalDataSource = cartLocalDataSource;
    }

    public static CartLocalRepository getInstance(CartLocalDataSource cartLocalDataSource){
        if (INSTANCE == null){
            INSTANCE = new CartLocalRepository(cartLocalDataSource);
        }

        return INSTANCE;
    }

    @Override
    public void addToCart(List<CartItem>cartItems , CartItem cartItem , SuccessCallback<List<CartItem>> successCallback) {
        cartLocalDataSource.addToCart(cartItems, cartItem , successCallback);
    }

    @Override
    public void deleteFromCart(CartItem cartItem, SuccessCallback<List<ShoppingCart>> successCallback) {

    }
}
