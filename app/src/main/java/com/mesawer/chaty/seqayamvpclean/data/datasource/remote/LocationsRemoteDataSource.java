package com.mesawer.chaty.seqayamvpclean.data.datasource.remote;

import com.mesawer.chaty.seqayamvpclean.data.datasource.LocationsDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public class LocationsRemoteDataSource implements LocationsDataSource {

    private static LocationsRemoteDataSource INSTANCE;

    private LocationsRemoteDataSource() {
    }

    public static LocationsRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new LocationsRemoteDataSource();
        return INSTANCE;
    }

    @Override
    public void addNewLocation(Location location,
                               SuccessCallback<Location> successCallback,
                               ErrorCallback errorCallback) {

    }

    @Override
    public void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                                  ErrorCallback errorCallback) {

    }
}
