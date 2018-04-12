package com.mesawer.chaty.seqayamvpclean.presentation.location;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.base.UseCaseHandler;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.location.AddLocationUseCase;

public class LocationPresenter implements LocationContract.Presenter{
    UseCaseHandler useCaseHandler;
    Location location;
    LocationContract.View mView;
    AddLocationUseCase addLocationUseCase;

    LocationPresenter (UseCaseHandler useCaseHandler,
                       LocationContract.View mView,
                       AddLocationUseCase addLocationUseCase,
                       Location location
    ){
        this.addLocationUseCase=addLocationUseCase;
        this.mView=mView;
        this.useCaseHandler=useCaseHandler;
        this.location=location;
    }
    @Override
    public void addLocation(boolean flag) {
        AddLocationUseCase.RequestValues requestValues=new AddLocationUseCase.RequestValues(location);
        useCaseHandler.execute(addLocationUseCase, requestValues, new UseCase.UseCaseCallback<AddLocationUseCase.ResponseValue>() {
            @Override
            public void onSuccess(AddLocationUseCase.ResponseValue response) {
                if (response!=null){
                    mView.addLocationSuccess();
                }else {
                    mView.addLocationFail();
                }

            }

            @Override
            public void onError() {

            }
        });

    }
}
