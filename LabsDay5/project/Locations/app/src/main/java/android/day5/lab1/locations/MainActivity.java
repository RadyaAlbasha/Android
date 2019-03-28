package android.day5.lab1.locations;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_RECUEST = 0;
    MyLocationListener myLoc;
    LocationManager locMgr;

    Geocoder geocoder;
    List<Address> addresses;
    private double latitude;
    private double longitude;
    private View currentView;
    boolean isGetLocation;
    boolean isSendLocation;
    boolean isShowLocation;
    public MainActivity() {
       // latitude=25;
        //longitude=10;
        latitude=0.0;
        longitude=0.0;
        isGetLocation = false;
        isSendLocation = false;
        isShowLocation =false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLoc = new MyLocationListener();
        geocoder = new Geocoder(this, Locale.getDefault());
        //locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    public void getLocation(View view) {
        isGetLocation =true;
        currentView = view;
        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
               ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},LOCATION_RECUEST);
        }
        else
        {
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLoc);
        }

    }

    public void sendLocation(View view) {

        currentView = view;
        if(!isGetLocation)
        {
            isGetLocation =true;
            isSendLocation = true;
            getLocation(view);
        }

        else
        {
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            //////////////////////show text view////////////////////////
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:"));
            sendIntent.putExtra("address", "12125551212");
            sendIntent.putExtra("sms_body", "help me\nAddress: "+address+"\nState: "+state+"\nCountry: "+country);
            startActivity(sendIntent);
        }

    }
    //bouns
    public void showLocation(View view) {
        currentView = view;
        if(!isGetLocation)
        {
            isGetLocation =true;
            isShowLocation =true;
            getLocation(view);
        }
        else
        {
            FragmentManager mgr;
            FragmentTransaction trnc;
            mgr = getSupportFragmentManager();
            MapViewFragment mf = new MapViewFragment(latitude,longitude);

            trnc = mgr.beginTransaction();
            if(mgr.getBackStackEntryCount() ==0)
                trnc.add(R.id.frameLayoutMap , mf ,"mapFreg");
            trnc.commit();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
        switch (requestCode)
        {
            case LOCATION_RECUEST:
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},LOCATION_RECUEST);
                }
                else
                {
                    locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLoc);
                }
            break;
        }
    }
    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Toast.makeText(MainActivity.this, "Latitude: "+latitude+"\nLongitude: "+longitude+"\nAltitude: "+location.getAltitude(), Toast.LENGTH_SHORT).show();
            locMgr.removeUpdates(myLoc);
            if(isSendLocation)
            {
                sendLocation(currentView);
                isSendLocation = false;
            }

            if(isShowLocation)
            {
                showLocation(currentView);
                isShowLocation = false;
            }

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
    }
}
