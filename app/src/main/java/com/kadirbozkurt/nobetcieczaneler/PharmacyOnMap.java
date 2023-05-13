package com.kadirbozkurt.nobetcieczaneler;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kadirbozkurt.nobetcieczaneler.databinding.ActivityPharmacyOnMapBinding;

public class PharmacyOnMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityPharmacyOnMapBinding binding;
    private Singleton singleton;
    private Pharmacy pharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPharmacyOnMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        singleton = Singleton.getInstance();
        pharmacy = singleton.getSelectedPharmacy();

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

        System.out.println("pharmacy = " + pharmacy);

        // Add a marker in Sydney and move the camera
        LatLng pharmacyLocation = new LatLng(pharmacy.getLatitude(), pharmacy.getLongitude());
        Marker pharmacyMarker = mMap.addMarker(new MarkerOptions().position(pharmacyLocation).title(pharmacy.getName()));
        float zoomLevel = 16.0f; //This value controls the zoom level
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pharmacyLocation, zoomLevel));

    }
}