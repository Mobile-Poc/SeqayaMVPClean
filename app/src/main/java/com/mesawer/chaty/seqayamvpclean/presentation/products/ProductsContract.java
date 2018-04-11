package com.mesawer.chaty.seqayamvpclean.presentation.products;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

import java.util.List;

public interface ProductsContract {

    interface View extends BaseView<Presenter> {
        void showProducts(List<Product> productList);
        void showSearchResult(List<Product> productList);
        void showSortResult();
        void sentFavouriteList(List<Product> favList);
    }

    interface Presenter extends BasePresenter {
        void deleteFavourite(String productId);
        void getFavourites();
        void getProduct();
        void searchProduct(String keyWord);
        void addToFavourite(Fav fav);
    }
}
