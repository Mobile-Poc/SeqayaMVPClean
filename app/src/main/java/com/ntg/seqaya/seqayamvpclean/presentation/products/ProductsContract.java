package com.ntg.seqaya.seqayamvpclean.presentation.products;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;
import com.ntg.seqaya.seqayamvpclean.domain.entity.CartItem;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;

import java.util.List;

public interface ProductsContract {

    interface View extends BaseView<Presenter> {
        void showProducts(List<Product> productList);
        void showSearchResult(List<Product> productList);
        void showSortResult();
        void sentFavouriteList(List<Product> favList);
    }

    interface Presenter extends BasePresenter {
        void addToCart(List<CartItem> cartItems , CartItem cartItem);
        void deleteFavourite(List<Product> favouriteList , Product fav);
        void getFavourites();
        void getProduct();
        void searchProduct(String keyWord);
        void addToFavourite(List<Product> favouriteList , Product fav);
    }
}
