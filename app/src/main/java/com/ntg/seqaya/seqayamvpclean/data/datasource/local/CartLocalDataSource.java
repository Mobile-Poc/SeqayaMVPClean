package com.ntg.seqaya.seqayamvpclean.data.datasource.local;

import com.google.gson.Gson;
import com.ntg.seqaya.seqayamvpclean.data.datasource.local.sharedprefernce.SharedPrferenceClient;
import com.ntg.seqaya.seqayamvpclean.domain.entity.CartItem;
import com.ntg.seqaya.seqayamvpclean.domain.entity.ShoppingCart;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;



public class CartLocalDataSource implements com.ntg.seqaya.seqayamvpclean.data.datasource.CartLocalDataSource {

    private static final String KEY = "CART";

    private static CartLocalDataSource INSTANCE;

    private CartLocalDataSource() {
    }

    public static CartLocalDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new CartLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void addToCart(List<CartItem>cartItems , CartItem cartItem , SuccessCallback<List<CartItem>> successCallback) {
        ShoppingCart shoppingCart = User.getShoppingCart();
        for (CartItem item:shoppingCart.getCartItemList()){
            if (item.getProduct().getId() == cartItem.getProduct().getId()){
                shoppingCart.getCartItemList().remove(item);
            }
        }
        shoppingCart.getCartItemList().add(cartItem);
            Gson gson = new Gson();
            String shoppingCartString = gson.toJson(shoppingCart.getCartItemList());
            SharedPrferenceClient.getSharedPreferencesClient(KEY)
                    .edit()
                    .putString(KEY, shoppingCartString)
                    .commit();
            successCallback.onSuccess(shoppingCart.getCartItemList());

    }

    @Override
    public void deleteFromCart(CartItem cartItem, SuccessCallback<List<ShoppingCart>> successCallback) {

    }
}
