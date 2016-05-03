package geo.company.ksl374.function_geolocation;

import android.location.Criteria;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.location.LocationListener;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.Log.*;

public class MainActivity extends AppCompatActivity {

    public double longitudeBest, latitudeBest;
    public double longitudeGPS, latitudeGPS;
    public double longitudeNetwork, latitudeNetwork;
    TextView longitudeValueBest, latitudeValueBest;
    TextView longitudeValueGPS, latitudeValueGPS;
    TextView longitudeValueNetwork, latitudeValueNetwork;
    TextView addressValue;

    //gps tracker class
    public GpsInfo gps;
    public GpsToAddress gpstoadress;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        longitudeValueBest = (TextView) findViewById(R.id.longitudeValueBest);
        latitudeValueBest = (TextView) findViewById(R.id.latitudeValueBest);
        longitudeValueGPS = (TextView) findViewById(R.id.longitudeValueGPS);
        latitudeValueGPS = (TextView) findViewById(R.id.latitudeValueGPS);
        longitudeValueNetwork = (TextView) findViewById(R.id.longitudeValueNetwork);
        longitudeValueNetwork = (TextView) findViewById(R.id.longitudeValueNetwork);
        latitudeValueNetwork = (TextView) findViewById(R.id.latitudeValueNetwork);
        addressValue = (TextView) findViewById(R.id.addressText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.func_geo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void toggleGPSUpdates(View view) throws Exception {
        Log.d("123", "123");
        gps = new GpsInfo(this);
     if(!gps.isGPSEnabled())
        return;
        else{
          latitudeGPS= gps.getGps_latitude();
         longitudeGPS = gps.getGps_longitude();
         latitudeValueGPS.setText(String.valueOf(latitudeGPS));
         longitudeValueGPS.setText(String.valueOf(longitudeGPS));

//         gpstoadress = new GpsToAddress(this, latitudeGPS, longitudeGPS);
  //          address = gpstoadress.getAddress();
         Toast.makeText(getApplicationContext(), "Gps신호가 업데이트 되었습니다\n",Toast.LENGTH_LONG).show();
     }
    }

    public void toggleNetworkUpdates(View view)
    {
        Log.d("123", "NetworkListener");
        gps = new GpsInfo(this);
        if(!gps.isNetWorkEnabled()){
            Toast.makeText(getApplicationContext(), "Network 신호가 잡히질 않습니다\n",Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            latitudeNetwork = gps.getNetwork_latitude();
            longitudeNetwork = gps.getNetwork_longitude();
            latitudeValueNetwork.setText(String.valueOf(latitudeNetwork));
            longitudeValueNetwork.setText(String.valueOf(longitudeNetwork));

            Toast.makeText(getApplicationContext(), "Network 신호가 업데이트 되었습니다\n",Toast.LENGTH_LONG).show();
        }
    }

    public void toggleBestUpdates(View view)
    {
        Log.d("123", "BestupdateListener");
        gps = new GpsInfo(this);
        if(!gps.isGPSEnabled())
            return;
        else{
            latitudeBest =  gps.getBest_latitude();
            longitudeBest = gps.getBest_longitude();
            latitudeValueBest.setText(String.valueOf(latitudeBest));
            longitudeValueBest.setText(String.valueOf(longitudeBest));
            Toast.makeText(getApplicationContext(), "Best 신호가 업데이트 되었습니다\n",Toast.LENGTH_LONG).show();

        }
    }
}
