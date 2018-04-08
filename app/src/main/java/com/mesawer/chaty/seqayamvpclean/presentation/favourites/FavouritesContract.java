package com.mesawer.chaty.seqayamvpclean.presentation.favourites;

import com.mesawer.chaty.seqayamvpclean.base.BasePresenter;
import com.mesawer.chaty.seqayamvpclean.base.BaseView;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

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
