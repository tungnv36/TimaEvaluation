package vn.tima.timainspection.Service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.greenrobot.event.EventBus;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.GPSTracker;

public class GPSService extends Service {

    private LocationManager locationManager;
    private MyLocationListener myLocationListener;
    private DatabaseReference mData;
    private GPSTracker gps;
    private UserInfo userInfo;
    public static double lattitude=0;
    public static double longitude=0;

    public GPSService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        this.myLocationListener = new MyLocationListener();
        gps = new GPSTracker(this);
        userInfo = UserInfo.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if(gps.canGetLocation()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, myLocationListener);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, myLocationListener);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.removeUpdates(myLocationListener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            try {
                mData.child(userInfo.getUser(GPSService.this).getUserName()).child("Lat").setValue(location.getLatitude());
                mData.child(userInfo.getUser(GPSService.this).getUserName()).child("Long").setValue(location.getLongitude());
            } catch (Exception e){
                Toast.makeText(GPSService.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            lattitude=location.getLatitude();
            longitude=location.getLongitude();
            EventBus.getDefault().post(location);
//            Toast.makeText(GPSService.this, location.getLatitude() + " - " + location.getLongitude(), Toast.LENGTH_LONG).show();
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
