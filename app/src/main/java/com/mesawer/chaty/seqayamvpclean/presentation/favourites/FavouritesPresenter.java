package com.mesawer.chaty.seqayamvpclean.presentation.favourites;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites.DeleteFavourite;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites.GetFavourites;

public class FavouritesPresenter implements FavouritesContract.Presenter {

    private FavouritesContract.View favouritesView;
    private GetFavourites getFavourites;
    private DeleteFavourite deleteFavourite;

    public FavouritesPresenter(FavouritesContract.View favouritesView,
                               GetFavourites getFavourites,
                               DeleteFavourite deleteFavourite) {
        this.favouritesView = favouritesView;
        this.getFavourites = getFavourites;
        this.deleteFavourite = deleteFavourite;
    }

    @Override
    public void getFavourites() {
        getFavourites.execute(null,
                response -> {
                    if (response.getFavs().isEmpty()) {
                        favouritesView.showNoFavourites();
                    } else {
                        favouritesView.showFavourites(response.getFavs());
                    }
                },
                favouritesView::showErrorMessage);
    }

    @Override
    public void removeFavourite(String productId) {

    }
}
