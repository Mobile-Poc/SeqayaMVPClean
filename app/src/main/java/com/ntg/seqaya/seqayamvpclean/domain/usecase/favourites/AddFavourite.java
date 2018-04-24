package com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IFavouritesLocalRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IFavouritesRepository;

import java.util.List;

public class AddFavourite extends UseCase<AddFavourite.RequestValues, AddFavourite.ResponseValues> {

    private IFavouritesLocalRepository favouritesRepository;

    public AddFavourite(IFavouritesLocalRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        favouritesRepository.addFav(requestValues.getFavouriteList() , requestValues.getFav());
    }

    public static final class RequestValues implements UseCase.RequestValues {
        Product fav;
        List<Product> favouriteList;
        public RequestValues(List<Product> favouriteList , Product fav) {
            this.favouriteList = favouriteList;
            this.fav = fav;
        }

        public Product getFav() {
            return fav;
        }

        public List<Product> getFavouriteList() {
            return favouriteList;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {}
}
