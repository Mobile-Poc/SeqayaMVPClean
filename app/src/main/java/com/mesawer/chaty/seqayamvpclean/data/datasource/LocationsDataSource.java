package com.mesawer.chaty.seqayamvpclean.data.datasource;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ILocationsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public interface LocationsDataSource {

    void addNewLocation(Location location,
                        ILocationsRepository.AddLocationCallBack callBack);

    void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                           ErrorCallback errorCallback);
}
