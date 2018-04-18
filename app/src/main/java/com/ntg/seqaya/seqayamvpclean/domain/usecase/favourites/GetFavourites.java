package com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites;

import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IFavouritesRepository;

import java.util.List;

public class GetFavourites implements
        UseCase<GetFavourites.RequestValues, GetFavourites.ResponseValues> {

    private IFavouritesRepository favouritesRepository;

    public GetFavourites(IFavouritesRepository favouritesRepository) {
        this.favouritesRepository = favouritesRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        favouritesRepository.getFavs(favs -> {
            ResponseValues responseValues = new ResponseValues(favs);
            successCallback.onSuccess(responseValues);
        }, errorCallback::onError);
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
