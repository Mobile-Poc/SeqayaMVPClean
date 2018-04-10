package com.mesawer.chaty.seqayamvpclean.domain.repository;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;

import java.util.List;

public interface ILocationsRepository {

    void addNewLocation(Location location,
                        SuccessCallback<Location> successCallback,
                        ErrorCallback errorCallback);

    void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                           ErrorCallback errorCallback);
}
