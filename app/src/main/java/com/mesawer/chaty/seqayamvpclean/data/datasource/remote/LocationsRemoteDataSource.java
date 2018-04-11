package com.mesawer.chaty.seqayamvpclean.data.datasource.remote;

import com.mesawer.chaty.seqayamvpclean.data.datasource.LocationsDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Location;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

import javax.xml.ws.Response;

import retrofit2.Call;
import retrofit2.Callback;

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
      ApiClient.getClient().create(ProductService.class).addNewLocation(location)
      .enqueue(new Callback<Location>() {
          @Override
          public void onResponse(Call<Location> call, retrofit2.Response<Location> response) {
              if (response.isSuccessful()){
                  Location result  = response.body();
                  if (location != null && result.getUserId().equals(location.getUserId())){
                      successCallback.onSuccess(null);
                  }
              }
          }

          @Override
          public void onFailure(Call<Location> call, Throwable t) {

          }
      });

    }

    @Override
    public void getSavedLocations(SuccessCallback<List<Location>> successCallback,
                                  ErrorCallback errorCallback) {

    }
}
