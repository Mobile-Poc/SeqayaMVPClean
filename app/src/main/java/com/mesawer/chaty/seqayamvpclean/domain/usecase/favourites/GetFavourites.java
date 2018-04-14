package com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IFavouritesRepository;

import java.util.List;

public class GetFavourites extends
        UseCase<GetFavourites.RequestValues, GetFavourites.ResponseValues> {

    private IFavouritesRepository favouritesRepository;

    public GetFavourites(IFavouritesRepository favouritesRepository) {
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
