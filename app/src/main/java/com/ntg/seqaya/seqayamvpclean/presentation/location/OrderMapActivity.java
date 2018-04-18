package com.ntg.seqaya.seqayamvpclean.presentation.location;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
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
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.presentation.location.savedlocations.SavedLocationsFragment;
import com.ntg.seqaya.seqayamvpclean.presentation.main.MainActivity;
import com.ntg.seqaya.seqayamvpclean.utils.Injection;
import com.ntg.seqaya.seqayamvpclean.utils.PresenterCache;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class OrderMapActivity extends FragmentActivity implements
        OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, LocationListener, LocationContract.View {
    final static String TAG = OrderMapActivity.class.getCanonicalName();
    LocationContract.Presenter mPresenter;
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

        if (!isNetworkConnected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("هناك خطأ");
            builder.setMessage("تأكد من الاتصال الأنترنت");
            builder.setPositiveButton("تم", (dialog, which) -> {
                dialog.cancel();
                OrderMapActivity.this.finish();
            });
            builder.show();
        }

        savedLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(MainActivity.ORDER, order);
                SavedLocationsFragment fragment = new SavedLocationsFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.container_id, fragment);// f1_container is your FrameLayout container
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();


            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setLocation(location);
                if (order.getLocation() == null) {
                    Toast.makeText(OrderMapActivity.this, "فضلا قم بإختيار موقع", Toast.LENGTH_SHORT).show();
                } else {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(MainActivity.ORDER, order);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("رجاء التأكد من الانترنت وتشغيل الموقع");
            dialog.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    getBaseContext().startActivity(myIntent);
                    //get gps
                    flag = true;
                }


            });
            dialog.setNegativeButton("لا اوافق", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    finish();

                }
            });
            dialog.show();
        }

        mGoogleMap = googleMap;
        //   mGoogleMap.setMyLocationEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(OrderMapActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION_LOCATION);
        } else {
            getCurrentLocation();
            MarkerInfo markerInfo = new MarkerInfo();

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
                if (addresses.size() > 0) {
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


    private void getCurrentLocation() {
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            currentLocation = new LatLng(latitude, longitude);
            mGoogleMap.addMarker(new MarkerOptions().position(currentLocation));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gps.getLatitude(), gps.getLongitude()), 12.0f));
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
                return;
            }
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        MarkerInfo markerInfo = (MarkerInfo) marker.getTag();
        finaladdress = markerInfo.getAddress();
        setinject(location);
        mPresenter.addLocation(true);


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

    @Override
    public void setPresenter(LocationContract.Presenter presenter) {

    }

    @Override
    public void showErrorMessage(String errMsg) {

    }

    @Override
    public void addLocationSuccess() {

    }

    @Override
    public void addLocationFail() {

    }

    void setinject(Location location) {
        PresenterCache.getInstance().addPresenter(OrderMapActivity.TAG,
                new LocationPresenter(Injection.provideUseCaseHandler(),
                        this,
                        Injection.injectAddLocationUseCase(),
                        location

                ));
        if (PresenterCache.getInstance().getPresenter(TAG) != null) {
            mPresenter = (LocationContract.Presenter) PresenterCache.getInstance().getPresenter(TAG);
        }
    }
}
