package com.ntg.seqaya.seqayamvpclean.data.datasource;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ILocationsRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public interface LocationsDataSource {

    void addNewLocation(Location location,
                        ILocationsRepository.AddLocationCallBack callBack);

    void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                           ErrorCallback errorCallback);
}
