package com.mesawer.chaty.seqayamvpclean.presentation.favourites;

import com.mesawer.chaty.seqayamvpclean.base.UseCaseHandler;
import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites.DeleteFavourite;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites.GetFavourites;

public class FavouritesPresenter implements FavouritesContract.Presenter {

    private UseCaseHandler useCaseHandler;
    private FavouritesContract.View favouritesView;
    private GetFavourites getFavourites;
    private DeleteFavourite deleteFavourite;

    public FavouritesPresenter(UseCaseHandler useCaseHandler,
                               FavouritesContract.View favouritesView,
                               GetFavourites getFavourites,
                               DeleteFavourite deleteFavourite) {
        this.useCaseHandler = useCaseHandler;
        this.favouritesView = favouritesView;
        this.getFavourites = getFavourites;
        this.deleteFavourite = deleteFavourite;
    }

    @Override
    public void getFavourites() {
        useCaseHandler.execute(getFavourites, null,
                new UseCase.UseCaseCallback<GetFavourites.ResponseValues>() {
                    @Override
                    public void onSuccess(GetFavourites.ResponseValues response) {
                        if (response.getFavs().isEmpty()) {
                            favouritesView.showNoFavourites();
                        } else {
                            favouritesView.showFavourites(response.getFavs());
                        }
                    }

                    @Override
                    public void onError(String errMsg) {
                        favouritesView.showErrorMessage(errMsg);
                    }
                });
    }

    @Override
    public void removeFavourite(String productId) {
        DeleteFavourite.RequestValues requestValues = new DeleteFavourite.RequestValues(productId);
        useCaseHandler.execute(deleteFavourite, requestValues,
                new UseCase.UseCaseCallback<DeleteFavourite.ResponseValues>() {
                    @Override
                    public void onSuccess(DeleteFavourite.ResponseValues response) {
                        favouritesView.removeFavourite(
                                Integer.parseInt(response.getFav().getProductId()));
                    }

                    @Override
                    public void onError(String errMsg) {
                        favouritesView.showErrorMessage(errMsg);
                    }
                });
    }
}
