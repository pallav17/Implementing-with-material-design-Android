package com.example.pallavshah.geospot;

/**
 * Created by PALLAV SHAH on 27-06-2015.
 */
      import java.io.IOException;
        import java.util.List;
        import java.util.Locale;

        import android.app.AlertDialog;
        import android.app.Service;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.location.Address;
        import android.location.Geocoder;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.os.IBinder;
        import android.provider.Settings;

public class GPSService extends Service implements LocationListener {


    private final Context mContext;


    boolean isGPSEnabled = false;

    boolean isNetworkEnabled = false;

    public boolean isLocationAvailable = false;


    Location mLocation;
    double mLatitude;
    double mLongitude;


    private static final long TIME = 30000;

    private static final long DISTANCE = 20;


    protected LocationManager mLocationManager;

    public GPSService(Context context) {
        this.mContext = context;
        mLocationManager = (LocationManager) mContext
                .getSystemService(LOCATION_SERVICE);

    }


    public Location getLocation() {
        try {


            isGPSEnabled = mLocationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);


            if (isGPSEnabled) {
                mLocationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, TIME, DISTANCE, this);
                if (mLocationManager != null) {
                    mLocation = mLocationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (mLocation != null) {
                        mLatitude = mLocation.getLatitude();
                        mLongitude = mLocation.getLongitude();
                        isLocationAvailable = true; // setting a flag that
                        // location is available
                        return mLocation;
                    }
                }
            }


            isNetworkEnabled = mLocationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isNetworkEnabled) {
                mLocationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, TIME, DISTANCE, this);
                if (mLocationManager != null) {
                    mLocation = mLocationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (mLocation != null) {
                        mLatitude = mLocation.getLatitude();
                        mLongitude = mLocation.getLongitude();
                        isLocationAvailable = true; // setting a flag that
                        // location is available
                        return mLocation;
                    }
                }
            }

            if (!isGPSEnabled) {

                askUserToOpenGPS();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        isLocationAvailable = false;
        return null;
    }


    public String getLocationAddress() {

        if (isLocationAvailable) {

            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

            List<Address> addresses = null;
            try {

                addresses = geocoder.getFromLocation(mLatitude, mLongitude, 1);
            } catch (IOException e1) {
                e1.printStackTrace();
                return (" ");
            } catch (IllegalArgumentException e2) {

                String errorString = "Illegal arguments "
                        + Double.toString(mLatitude) + " , "
                        + Double.toString(mLongitude)
                        + " passed to address service";
                e2.printStackTrace();
                return errorString;
            }

            if (addresses != null && addresses.size() > 0) {

                Address address = addresses.get(0);
				/*
				 * Format the first line of address (if available), city, and
				 * country name.
				 */
                String addressText = String.format(
                        "%s,%s",

                        address.getMaxAddressLineIndex() > 0 ? address
                                .getAddressLine(0) : "",

                        address.getLocality(),

                        address.getCountryName());

                return addressText;
            } else {
                return "No address found by the service.";
            }
        } else {
            return "Location Not available";
        }

    }



    /**
     * get latitude
     *
     * @return latitude in double
     */
    public double getLatitude() {
        if (mLocation != null) {
            mLatitude = mLocation.getLatitude();
        }
        return mLatitude;
    }

    /**
     * get longitude
     *
     * @return longitude in double
     */
    public double getLongitude() {
        if (mLocation != null) {
            mLongitude = mLocation.getLongitude();
        }
        return mLongitude;
    }

    /**
     * close GPS to save battery
     */
    public void closeGPS() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(GPSService.this);
        }
    }

    /**
     * show settings to open GPS
     */
    public void askUserToOpenGPS() {
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        mAlertDialog.setTitle("Location not available, Open GPS?")
                .setMessage("Activate GPS to use use location services?")
                .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    /**
     * Updating the location when location changes
     */
    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
