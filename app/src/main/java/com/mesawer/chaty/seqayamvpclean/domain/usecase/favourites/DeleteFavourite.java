package com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IFavouritesRepository;

public class DeleteFavourite extends UseCase<DeleteFavourite.RequestValues, DeleteFavourite.ResponseValues> {

    private IFavouritesRepository favouritesRepository;

    public DeleteFavourite(IFavouritesRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        favouritesRepository.deleteFav(requestValues.getProductId()
                , fav -> {
                    ResponseValues responseValues = new ResponseValues(fav);
                    getUseCaseCallback().onSuccess(responseValues);
                },
                getUseCaseCallback()::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        String productId;

        public RequestValues(String productId) {
            this.productId = productId;
        }

        public String getProductId() {
            return productId;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
        private Fav fav;

        public ResponseValues(Fav fav) {
            this.fav = fav;
        }

        public Fav getFav() {
            return fav;
        }
    }
}
