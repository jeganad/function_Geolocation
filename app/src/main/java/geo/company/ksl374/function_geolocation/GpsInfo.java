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

/**
 * Created by 4sizn on 2016. 5. 1..
 */
public class GpsInfo extends Service implements LocationListener {
    private final Context mContext;

    //gps_allow_distance
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATE = 10;

    //check the update gps in time
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    boolean isGPSEnabled = false;
    boolean isNetWorkEnabled = false;

    protected  LocationManager locationManager;

    private double gps_latitude;
    private double gps_longitude;
   /* private double netwrok_latitude;
    private double network_logitude;
    private double best_latitude;
    private double best_longigude;*/
    Location location;


    public GpsInfo(Context mContext) {
        this.mContext = mContext;
        getLocation();
    }

    public Location getLocation()
    {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        return location;
    }

    public boolean checkLocation(){
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private boolean isLocationEnabled(){
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
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
}

