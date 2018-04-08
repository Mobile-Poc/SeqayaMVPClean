package com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

import java.util.List;

public class GetFavourites implements
        UseCase<GetFavourites.RequestValues, GetFavourites.ResponseValues> {

    private IProductsRepository productsRepository;

    public GetFavourites(IProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        productsRepository.getFavs(favs -> {
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
