package com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IFavouritesRepository;

public class AddFavourite implements UseCase<AddFavourite.RequestValues, AddFavourite.ResponseValues> {

    private IFavouritesRepository iFavouritesRepository;

    public AddFavourite(IFavouritesRepository iFavouritesRepository) {
        this.iFavouritesRepository = iFavouritesRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        iFavouritesRepository.addFav(requestValue.getFav() , result -> {
            successCallback.onSuccess(null);
        },errorCallback::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues{
        Fav fav;
        public RequestValues(Fav fav) {
            this.fav = fav;
        }

        public Fav getFav() {
            return fav;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues{

    }
}
