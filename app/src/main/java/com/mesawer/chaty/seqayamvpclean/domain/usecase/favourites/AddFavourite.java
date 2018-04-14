package com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IFavouritesRepository;

public class AddFavourite extends UseCase<AddFavourite.RequestValues, AddFavourite.ResponseValues> {

    private IFavouritesRepository favouritesRepository;

    public AddFavourite(IFavouritesRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        favouritesRepository.addFav(requestValues.getFav(),
                result -> getUseCaseCallback().onSuccess(null),
                getUseCaseCallback()::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        Fav fav;

        public RequestValues(Fav fav) {
            this.fav = fav;
        }

        public Fav getFav() {
            return fav;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {}
}
