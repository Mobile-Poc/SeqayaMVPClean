package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import android.support.annotation.NonNull;

import com.ntg.seqaya.seqayamvpclean.data.datasource.LocationsDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ILocationsRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
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
    public void addNewLocation(Location location, ILocationsRepository.AddLocationCallBack callBack) {
        try {
            Response<Location> response = ApiClient.getClient().create(ProductService.class).addNewLocation(location).execute();
            callBack.onSuccess(response.body());
        } catch (Exception e) {
            callBack.onFail();
        }
    }

    @Override
    public void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                                  ErrorCallback errorCallback) {
        ApiClient.getClient().create(ProductService.class)
                .getSavedLocations(User.getEmail())
                .enqueue(new Callback<List<Location>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Location>> call,
                                           @NonNull Response<List<Location>> response) {
                        if (response.isSuccessful()) {
                            List<Location> locations = response.body();
                            successCallback.onSuccess(locations);
                        } else {
                            try {
                                errorCallback.onError(apiErrMsg(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Location>> call, @NonNull Throwable t) {

                    }
                });
    }
}
