package com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IFavouritesRepository;

public class DeleteFavourite implements
        UseCase<DeleteFavourite.RequestValues, DeleteFavourite.ResponseValues> {

    private IFavouritesRepository favouritesRepository;

    public DeleteFavourite(IFavouritesRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        favouritesRepository.deleteFav(requestValue.getProductId()
                , result -> successCallback.onSuccess(null), errMsg -> {});
    }

    public static final class RequestValues implements UseCase.RequestValues{
        String productId;

        public RequestValues(String productId) {
            this.productId = productId;
        }

        public String getProductId() {
            return productId;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues{

    }
}
