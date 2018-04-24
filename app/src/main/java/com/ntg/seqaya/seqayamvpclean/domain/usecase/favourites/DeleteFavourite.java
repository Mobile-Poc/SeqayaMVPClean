package com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IFavouritesLocalRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IFavouritesRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DeleteFavourite extends UseCase<DeleteFavourite.RequestValues, DeleteFavourite.ResponseValues> {

    private IFavouritesLocalRepository favouritesRepository;

    public DeleteFavourite(IFavouritesLocalRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues ) {
        favouritesRepository.deleteFav(requestValues.favouriteList , requestValues.getFav());
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private Product fav;
        private List<Product> favouriteList = new CopyOnWriteArrayList<>();


        public RequestValues(Product fav, List<Product> favouriteList) {
            this.fav = fav;
            this.favouriteList = favouriteList;
        }

        public Product getFav() {
            return fav;
        }

        public List<Product> getFavouriteList() {
            return favouriteList;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
        private List<Product> favouriteList;

        public ResponseValues(List<Product> favouriteList) {
            this.favouriteList = favouriteList;
        }

        public List<Product> getFavouriteList() {
            return favouriteList;
        }
    }
}
