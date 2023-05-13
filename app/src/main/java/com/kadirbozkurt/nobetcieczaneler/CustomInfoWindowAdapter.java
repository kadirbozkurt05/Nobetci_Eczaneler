package com.kadirbozkurt.nobetcieczaneler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;
    private Pharmacy pharmacy;

    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void renderWindowText(Marker marker, View view) {
        // This method sets the custom view's text and image
        TextView title = view.findViewById(R.id.title);
        TextView address = view.findViewById(R.id.address);
        TextView phone = view.findViewById(R.id.phone);
        ImageView image = view.findViewById(R.id.image);
        Singleton singleton = Singleton.getInstance();
        pharmacy = singleton.getSelectedPharmacy();
        view.setLayoutParams(new RelativeLayout.LayoutParams(100, RelativeLayout.LayoutParams.WRAP_CONTENT));


        title.setText(pharmacy.getName());
        address.setText(pharmacy.getAdress());
        phone.setText("Tel : "+pharmacy.getPhone()); // Set a hardcoded phone number
        image.setImageResource(R.drawable.eczane); // Set the custom image resource
    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
