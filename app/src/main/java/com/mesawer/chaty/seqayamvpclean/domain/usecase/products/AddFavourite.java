package com.mesawer.chaty.seqayamvpclean.domain.usecase.products;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;

public class AddFavourite implements UseCase<AddFavourite.RequestValues, AddFavourite.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
    }

    public static final class RequestValues implements UseCase.RequestValues{

    }

    public static final class ResponseValues implements UseCase.ResponseValues{

    }
}
