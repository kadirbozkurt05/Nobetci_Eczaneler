package com.kadirbozkurt.nobetcieczaneler;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

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
import com.kadirbozkurt.nobetcieczaneler.databinding.ActivityPharmacyOnMapBinding;
public class PharmacyOnMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityPharmacyOnMapBinding binding;
    private Singleton singleton;
    private Pharmacy pharmacy;
    private SharedPreferences sharedPreferences;
    private int theme;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences =this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        theme = sharedPreferences.getInt("theme",android.R.style.Theme);
        setTheme(theme);
        super.onCreate(savedInstanceState);

        binding = ActivityPharmacyOnMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        singleton = Singleton.getInstance();
        pharmacy = singleton.getSelectedPharmacy();

        Utils.clickEffect(binding.callButton);
        Utils.clickEffect(binding.shareButton);
        Utils.clickEffect(binding.navigateButton);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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
        mMap = googleMap;
        MapStyleOptions retroStyle = MapStyleOptions.loadRawResourceStyle(
                this, R.raw.maps_retro);
        MapStyleOptions darkStyle = MapStyleOptions.loadRawResourceStyle(this,R.raw.maps_dark);

        switch (theme){
            case R.style.Theme_AppCompat:
                mMap.setMapStyle(darkStyle);
                break;
            default:
                mMap.setMapStyle(retroStyle);
                break;
        }

        // Add a marker in Sydney and move the camera
        // Create the custom InfoWindowAdapter and set it on the GoogleMap object
        CustomInfoWindowAdapter infoWindowAdapter = new CustomInfoWindowAdapter(this);
        mMap.setInfoWindowAdapter(infoWindowAdapter);

// Add a marker to the map and show the InfoWindow
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.eczane), 120, 120, false));
        LatLng pharmacyLocation = new LatLng(pharmacy.getLatitude(), pharmacy.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(pharmacyLocation).title(pharmacy.getName()).snippet(pharmacy.getAdress()).icon(icon);
        Marker marker = mMap.addMarker(markerOptions);
        marker.showInfoWindow();


// Zoom to the marker
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pharmacyLocation, 16));


    }
    public void call(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + pharmacy.getPhone()));
        startActivity(intent);
    }
    public void route(View v){
        String uri = "google.navigation:q=" + pharmacy.getLatitude() + "," + pharmacy.getLongitude();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
    public void share(View v){
        String latitude =""+ pharmacy.getLatitude();
        String longitude =""+ pharmacy.getLongitude();
        String url = pharmacy.getName()+"\n"+pharmacy.getPhone()+"\nhttps://www.google.com/maps/search/?api=1&query="+latitude+","+longitude;
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/url");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, pharmacy.getName());
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(shareIntent, "Paylaş"));
    }
}