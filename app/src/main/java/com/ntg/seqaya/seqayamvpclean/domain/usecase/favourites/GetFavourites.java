package com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IFavouritesLocalRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IFavouritesRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GetFavourites extends
        UseCase<GetFavourites.RequestValues, GetFavourites.ResponseValues> {

    private IFavouritesLocalRepository favouritesRepository;

    public GetFavourites(IFavouritesLocalRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        favouritesRepository.getFavs(favs -> {
            ResponseValues responseValues = new ResponseValues(favs);
            getUseCaseCallback().onSuccess(responseValues);
        }, getUseCaseCallback()::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
        private List<Product> favs;

        public ResponseValues(List<Product> favs) {
            this.favs = favs;
        }

        public List<Product> getFavs() {
            return favs;
        }
    }
}
