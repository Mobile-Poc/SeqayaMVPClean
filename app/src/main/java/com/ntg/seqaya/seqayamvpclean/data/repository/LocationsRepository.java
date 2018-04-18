package com.ntg.seqaya.seqayamvpclean.data.repository;

import com.ntg.seqaya.seqayamvpclean.data.datasource.LocationsDataSource;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ILocationsRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public class LocationsRepository implements ILocationsRepository {

    private static LocationsRepository INSTANCE;
    private LocationsDataSource locationsDataSource;

    private LocationsRepository(LocationsDataSource locationsDataSource) {
        this.locationsDataSource = locationsDataSource;
    }

    public static LocationsRepository getInstance(LocationsDataSource locationsDataSource) {
        if (INSTANCE == null) INSTANCE = new LocationsRepository(locationsDataSource);
        return INSTANCE;
    }

    @Override
    public void addNewLocation(Location location, AddLocationCallBack callBack) {
        locationsDataSource.addNewLocation(location,callBack);
    }

    @Override
    public void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                                  ErrorCallback errorCallback) {
        locationsDataSource.getSavedLocations(successCallback, errorCallback);
    }
}
