package geo.company.ksl374.function_geolocation;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 4sizn on 2016. 5. 1..
 */
public class GpsInfo extends Service implements LocationListener {
    private final Context mContext;

    //gps_allow_distance
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

    //check the update gps in time
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    boolean isGPSEnabled = false;
    boolean isNetWorkEnabled = false;

    protected  LocationManager locationManager;

    private double gps_latitude;
    private double gps_longitude;

    private double netwrok_latitude;
    private double network_longitude;
    /*private double best_latitude;
    private double best_longigude;*/
    Location location;


    public GpsInfo(Context mContext) {
        this.mContext = mContext;
        getLocation();
    }

    public Location getLocation()
    {
        try {
            locationManager = (LocationManager)mContext.getSystemService(LOCATION_SERVICE);
            if (checkNetworkState()) {
                Log.d("123", "locationManager is passed");
                if (isGPSEnabled()) {
                    if(location == null) // 처음 접근하면
                    {
                        Log.d("123", "location first start");
                        locationManager.requestLocationUpdates(
                                LocationManager.PASSIVE_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if (locationManager != null) {
                            Log.d("123", String.valueOf(LocationManager.GPS_PROVIDER));
                            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                            Log.d("123", String.valueOf(location));
                        }
                        if (location != null) {
                            Log.d("123", "Latitude is "+ location.getLatitude());
                            setGps_latitude(location.getLatitude());
                            setGps_longitude(location.getLongitude());
                        }
                }

                    if(isNetWorkEnabled())
                    {
                        Log.d("123", "network first start");
                        locationManager.requestLocationUpdates(
                            LocationManager.PASSIVE_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if(locationManager!=null)
                        {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                    if(location != null)
                                    {
                                        setNetwrok_latitude(location.getLatitude());
                                        setNetwork_longitude(location.getLongitude());
                                    }

                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return location;
    }

    public boolean checkNetworkState(){
        if(!isGPSEnabled() && !isNetWorkEnabled())
            showAlert();
        return isGPSEnabled();
    }

    private boolean isGPSEnabled(){
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private boolean isNetWorkEnabled(){
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("Enable Location").setMessage("Your Locations Setting is set to 'Off'.\nPlease Enable Location to " + "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(myIntent);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
    @Override
    public void onLocationChanged(Location location) {

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public double getGps_latitude()
    {
        return gps_latitude;
    }
    public double getGps_longitude()
    {
        return gps_longitude;
    }
    public void setGps_latitude(double latitude)
    {
        gps_latitude = latitude;
    }
    public void setGps_longitude(double longitude)
    {
        gps_longitude = longitude;
    }
    public double getNetwork_latitude()
    {
        return netwrok_latitude;
    }
    public double getNetwork_longitude()
    {
        return network_longitude;
    }
    public void setNetwrok_latitude(double latitude)
    {
        netwrok_latitude = latitude;
    }
    public void setNetwork_longitude(double longitude)
    {
        network_longitude = longitude;
    }


}

