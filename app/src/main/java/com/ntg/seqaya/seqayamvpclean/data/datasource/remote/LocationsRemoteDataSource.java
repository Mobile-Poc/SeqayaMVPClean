package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import com.ntg.seqaya.seqayamvpclean.data.datasource.LocationsDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

import static com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.Util.apiErrMsg;

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
        try {
            Response<List<Location>> response = ApiClient.getClient().create(ProductService.class)
                    .getSavedLocations(User.getEmail()).execute();
            if (response.isSuccessful()){
                List<Location> locations = response.body();
                successCallback.onSuccess(locations);
            } else {
                try {
                    errorCallback.onError(apiErrMsg(response.errorBody().string()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }
}
