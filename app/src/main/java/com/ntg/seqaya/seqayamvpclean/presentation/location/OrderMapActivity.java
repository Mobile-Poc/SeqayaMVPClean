package com.ntg.seqaya.seqayamvpclean.presentation.location;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseActivity;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.presentation.main.MainActivity;
import com.ntg.seqaya.seqayamvpclean.presentation.savedlocatioon.SavedLocationsFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class OrderMapActivity extends BaseActivity implements
        OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, LocationListener {

    private static final String TAG = OrderMapActivity.class.getSimpleName();
    private final int REQUEST_PERMISSION_LOCATION = 101;
    ImageView backImage;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    GoogleApiClient mGoogleApiClient;
    Marker mCurrLocationMarker;
    GPSTracker gps;
    public static boolean isMyLocationSet = false;
    LatLng currentLocation;
    double lattiude, longtude;
    Bundle bundle = new Bundle();
    Button nextButton, savedLocationButton;
    Order order;
    String finaladdress;
    Location location;
    LocationManager locationManager;
    String provider;
    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //checkLocationPermission();

        order = (Order) getIntent().getExtras().getSerializable(MainActivity.ORDER);
        nextButton = findViewById(R.id.next_button_id);
        savedLocationButton = findViewById(R.id.saved_location_button_id);
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        gps = new GPSTracker(this);
        if (isNetworkNotConnected())
            showNoNetworkConnectionDialog();

        savedLocationButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(MainActivity.ORDER, order);
            SavedLocationsFragment fragment = new SavedLocationsFragment();
            fragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.container_id, fragment);// f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();


        });
        nextButton.setOnClickListener(v -> {
            if (isNetworkNotConnected())
                showNoNetworkConnectionDialog();
            else {
                backToOrderFlow();
            }

        });
    }

    private void backToOrderFlow() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.ORDER, order);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void showNoNetworkConnectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("تأكد من اتصال الانترنت");
        builder.setPositiveButton("موافق",
                (dialog, which) -> openSettings(Settings.ACTION_WIFI_SETTINGS));
        builder.show();
    }

    private boolean isNetworkNotConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return (cm != null ? cm.getActiveNetworkInfo() : null) == null;
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (!isGPSConnected()) {
            showTurnOnLocationDialog();
        }

        mGoogleMap = googleMap;
        //   mGoogleMap.setMyLocationEnabled(true);
        if (isLocationPermissionGranted()) {
            getCurrentLocation();
        } else {
            requestLocationPermission();
        }

        mGoogleMap.setOnInfoWindowClickListener(this);

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            List<Address> addresses;

            @Override
            public void onMapClick(final LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                mGoogleMap.clear();

                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mGoogleMap.addMarker(markerOptions);


                bundle.putDouble("LAT", latLng.latitude);
                bundle.putDouble("LNG", latLng.longitude);
                Geocoder geocoder;
                addresses = null;

                geocoder = new Geocoder(OrderMapActivity.this, Locale.getDefault());


                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    Toast.makeText(OrderMapActivity.this, address, Toast.LENGTH_SHORT).show();
                    finaladdress = address;
                }
                location = new Location(User.getEmail(), finaladdress, latLng.latitude, latLng.longitude);

            }
        });

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            List<Address> addresses;
            Geocoder geocoder;

            @Override
            public boolean onMarkerClick(Marker marker) {
                lattiude = marker.getPosition().latitude;
                longtude = marker.getPosition().longitude;
                geocoder = new Geocoder(OrderMapActivity.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(lattiude, longtude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }

                bundle.putDouble("LAT", lattiude);
                bundle.putDouble("LNG", longtude);
                if (addresses != null && !addresses.isEmpty()) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                    finaladdress = address;
                    location = new Location(User.getEmail(), finaladdress, lattiude, longtude);

                    MarkerInfo markerInfo = new MarkerInfo();
                    markerInfo.setAddress(address);
                    marker.setTag(markerInfo);
                    marker.showInfoWindow();
                    CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(OrderMapActivity.this);
                    googleMap.setInfoWindowAdapter(customInfoWindow);
                    onInfoWindowClick(marker);
                }

                return false;
            }
        });
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(OrderMapActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSION_LOCATION);
    }

    private boolean isLocationPermissionGranted() {
        return ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED;
    }

    private void showTurnOnLocationDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("الرجاء التأكد من تشغيل الموقع");
        dialog.setPositiveButton("موافق", (paramDialogInterface, paramInt) -> {
            openSettings(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            //get gps
            flag = true;
        });
        dialog.setNegativeButton("لا اوافق", (paramDialogInterface, paramInt) -> finish());
        dialog.show();
    }

    private void openSettings(String action) {
        Intent intent = new Intent(action);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        getBaseContext().startActivity(intent);
    }

    private boolean isGPSConnected() {
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            if (lm != null) {
                gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            }
        } catch (Exception ex) {
            Log.d(TAG, "onMapReady: " + ex.getMessage());
        }

        try {
            if (lm != null) {
                network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }
        } catch (Exception ex) {
            Log.d(TAG, "onMapReady: " + ex.getMessage());
        }
        return gps_enabled && network_enabled;
    }

    private void getCurrentLocation() {
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            currentLocation = new LatLng(latitude, longitude);
            mGoogleMap.addMarker(new MarkerOptions().position(currentLocation));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gps.getLatitude(), gps.getLongitude()), 12.0f));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    finish();
                    startActivity(getIntent());
                } else {
                    Toast.makeText(OrderMapActivity.this, "!!!!", Toast.LENGTH_SHORT).show();
                }
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    //Request location updates:
                    getCurrentLocation();
                }
            }
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        MarkerInfo markerInfo = (MarkerInfo) marker.getTag();
        if (markerInfo != null) {
            finaladdress = markerInfo.getAddress();
        }

        ApiClient.getClient().create(ProductService.class).addNewLocation(location)
                .enqueue(new Callback<Location>() {
                    @Override
                    public void onResponse(Call<Location> call, Response<Location> response) {
                        Location loc = response.body();
                        order.setLocation(loc);
                    }

                    @Override
                    public void onFailure(Call<Location> call, Throwable t) {
                        showErrorMessage("تأكد من اتصال الانترنت");
                    }
                });

    }


    @Override
    public void onLocationChanged(android.location.Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (flag) {
            finish();
            Intent i = new Intent(this, OrderMapActivity.class);
            i.putExtra(MainActivity.ORDER, order);
            startActivity(i);
            flag = false;
        }
    }
}
