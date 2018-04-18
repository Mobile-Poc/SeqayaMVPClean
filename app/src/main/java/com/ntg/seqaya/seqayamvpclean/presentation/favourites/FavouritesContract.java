package com.ntg.seqaya.seqayamvpclean.presentation.favourites;

import com.ntg.seqaya.seqayamvpclean.base.BasePresenter;
import com.ntg.seqaya.seqayamvpclean.base.BaseView;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;

import java.util.List;

public interface FavouritesContract {

    interface View extends BaseView<Presenter> {
        void showFavourites(List<Product> favourites);

        void showNoFavourites();

        void removeFavourite(Product Product);
    }

    interface Presenter extends BasePresenter {
        void getFavourites();

        void removeFavourite(String productId);
    }
}
