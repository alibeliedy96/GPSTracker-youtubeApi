package com.example.gpstracker.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.List;


public class MyLocationProvider {
    LocationManager locationManager;
    Location location;
    boolean canGetLocation;
    Context context;
    LocationListener locationListener;

    public final int MIN_TIME_BETWEEN_UPDATES=5*1000;
    public final int MIN_DISTANCE_BETWEEN_UPDATES=10;


    public MyLocationProvider(Context context, LocationListener locationListener){
        this.context=context;
        locationManager= (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        location =null;
        this.locationListener=locationListener;
    }

    @SuppressLint("MissingPermission")
    public Location getCurrentLocation(){
        String provider=null;
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            provider = LocationManager.GPS_PROVIDER;
        }else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            provider= LocationManager.NETWORK_PROVIDER;
        }

        if(provider==null){
            canGetLocation=false;
            return null;
        }
        canGetLocation=true;

      location= locationManager.getLastKnownLocation(provider);

      if(locationListener!=null){
          locationManager.requestLocationUpdates(provider,
                  MIN_TIME_BETWEEN_UPDATES,
                  MIN_DISTANCE_BETWEEN_UPDATES,
                  locationListener);
      }

      if(location==null){
          location=getBestLastKnownLocation();
      }

      return location;

    }

    @SuppressLint("MissingPermission")
    Location getBestLastKnownLocation(){
        Location bestLocation=null;
      List<String> providers=
              locationManager.getProviders(true);
        for (String provider: providers) {
             Location l=locationManager.getLastKnownLocation(provider);
             if(bestLocation==null&&l!=null){
                 bestLocation=l;
                 continue;
             }else if(l!=null&&bestLocation!=null&&
                     bestLocation.getAccuracy()<l.getAccuracy()){
                 bestLocation=l;
             }
        }

        return bestLocation;

    }

}
