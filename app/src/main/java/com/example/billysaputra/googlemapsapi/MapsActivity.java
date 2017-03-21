package com.example.billysaputra.googlemapsapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public static Marker marker;
    private LatLng latLng = new LatLng(-6.121435, 106.774124);

    private static final String TAG = MapsActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*Calendar time = Calendar.getInstance();
        int a = time.get(Calendar.AM_PM); //return bool data type 1/0, 1 for PM and 0 for AM
        int b = time.get(Calendar.HOUR); //return 2 digit of hour in 12 Hour Format
        int hourOfDay = time.get(Calendar.HOUR_OF_DAY); //return 2 digit of hour in 24 Hour Format
        //Toast.makeText(this, "ini jam skrg"+hourOfDay, Toast.LENGTH_LONG).show();
        if(hourOfDay>=18||hourOfDay<=06){
            //jam 6 sore sampai jam 6 pagi
            try {
                // Customise the styling of the base map using a JSON object defined
                // in a raw resource file.
                boolean success = googleMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                this, R.raw.map_style_night));

                if (!success) {
                    Log.e(TAG, "Style parsing failed.");
                }
            } catch (Resources.NotFoundException e) {
                Log.e(TAG, "Can't find style. Error: ", e);
            }
        }else{
            //jam 6 pagi sampai jam 6 sore
            try {
                // Customise the styling of the base map using a JSON object defined
                // in a raw resource file.
                boolean success = googleMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                this, R.raw.map_style_plain));

                if (!success) {
                    Log.e(TAG, "Style parsing failed.");
                }
            } catch (Resources.NotFoundException e) {
                Log.e(TAG, "Can't find style. Error: ", e);
            }
        }
        String currentTimeString = DateFormat.getTimeInstance().format(new Date());
        Toast.makeText(this, "Current Time : "+currentTimeString, Toast.LENGTH_SHORT).show();

        mMap = googleMap;
        // Add a marker at the coord and move the camera
        LatLng jakarta = new LatLng(-6.225582, 106.7753963);
        mMap.addMarker(new MarkerOptions()
                .position(jakarta)
                .title("PT Sola Interactive")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .draggable(true));
        CameraUpdate camereUpdate = CameraUpdateFactory.newLatLng(jakarta);
        mMap.moveCamera(camereUpdate);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));*/

        mMap = googleMap;
        marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Animated marker"));
        Timer timer = new Timer();
        TimerTask updateProfile = new CustomTimerHandler(this);
        timer.scheduleAtFixedRate(updateProfile, 10, 2000);
        //timer.cancel();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
        


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                /*locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                if( !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(R.string.gps_not_found_title);  // GPS not found
                    builder.setMessage(R.string.gps_not_found_message); // Want to enable?
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            owner.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    });
                    builder.setNegativeButton(R.string.no, null);
                    builder.create().show();
                    return;
                }*/
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                return false;
            }
        });
    }
}
