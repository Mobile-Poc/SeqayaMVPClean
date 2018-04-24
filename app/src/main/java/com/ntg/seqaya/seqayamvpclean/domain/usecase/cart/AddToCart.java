package com.ntg.seqaya.seqayamvpclean.domain.usecase.cart;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.CartItem;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ICartLocalRepository;

import java.util.List;

/**
 * Created by islam on 4/23/2018.
 */

public class AddToCart extends UseCase<AddToCart.RequestValues , AddToCart.ResponseValues> {

    private ICartLocalRepository iCartLocalRepository;

    public AddToCart(ICartLocalRepository iCartLocalRepository) {
        this.iCartLocalRepository = iCartLocalRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        iCartLocalRepository.addToCart(requestValues.getCartItems(),requestValues.cartItem , result -> {
            ResponseValues responseValues = new ResponseValues(result);
            getUseCaseCallback().onSuccess(responseValues);
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        List<CartItem> cartItems;
        CartItem cartItem;

        public RequestValues(List<CartItem> cartItems , CartItem cartItem) {
            this.cartItem = cartItem;
            this.cartItems = cartItems;
        }

        public List<CartItem> getCartItems() {
            return cartItems;
        }

        public CartItem getCartItem() {
            return cartItem;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
        List<CartItem> cartItems;

        public ResponseValues(List<CartItem> cartItems) {
            this.cartItems = cartItems;
        }

        public List<CartItem> getCartItems() {
            return cartItems;
        }
    }
}
