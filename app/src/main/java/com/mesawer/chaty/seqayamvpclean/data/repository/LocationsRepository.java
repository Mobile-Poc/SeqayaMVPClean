package com.mesawer.chaty.seqayamvpclean.data.repository;

import com.mesawer.chaty.seqayamvpclean.data.datasource.LocationsDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ILocationsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public class LocationsRepository implements ILocationsRepository{

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
    public void addNewLocation(Location location, SuccessCallback<Location> successCallback,
                               ErrorCallback errorCallback) {

    }

    @Override
    public void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                                  ErrorCallback errorCallback) {

    }
}
