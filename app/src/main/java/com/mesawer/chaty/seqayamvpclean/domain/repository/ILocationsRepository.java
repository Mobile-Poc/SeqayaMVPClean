package com.mesawer.chaty.seqayamvpclean.domain.repository;

import com.mesawer.chaty.seqayamvpclean.data.datasource.LocationsDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;

import java.util.List;

public interface ILocationsRepository {
    interface AddLocationCallBack{
        void onSuccess(Location location);
        void onFail();
    }

    void addNewLocation(Location location,
                       AddLocationCallBack callBack);

    void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                           ErrorCallback errorCallback);
}
