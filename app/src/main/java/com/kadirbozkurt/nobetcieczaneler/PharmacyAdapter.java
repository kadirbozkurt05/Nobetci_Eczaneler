package com.kadirbozkurt.nobetcieczaneler;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder> {
    private ArrayList<Pharmacy> allPharmacies;
    private Pharmacy pharmacy;
    public PharmacyAdapter(ArrayList<Pharmacy> allPharmacies) {
        this.allPharmacies = allPharmacies;
    }

    @NonNull
    @Override
    public PharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pharmacy, parent, false);
        return new PharmacyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyViewHolder holder, int position) {

         pharmacy = allPharmacies.get(position);

        // Set click listener on the item view
        holder.pharmacyName.setText(pharmacy.getName());
        holder.pharmacyClose.setText("TARİF : "+pharmacy.getWhereToClose());
        holder.pharmacyPhone.setText("TELEFON : "+pharmacy.getPhone());
        holder.pharmacyAddress.setText("ADRES : "+pharmacy.getAdress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pharmacy clickedPharmacy = allPharmacies.get(position);
                System.out.println(clickedPharmacy.getLocationUrl());
                System.out.println(clickedPharmacy.getLatitude()+"    "+clickedPharmacy.getLongitude());
            }
        });

        holder.goToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + pharmacy.getLatitude() + "," + pharmacy.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(holder.itemView.getContext().getPackageManager()) != null) {
                   holder.itemView.getContext().startActivity(mapIntent);
                } else {
                    // Maps app is not installed on this device
                }


            }
        });

        holder.getGoToButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create the intent to launch the phone app with the phone number
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + pharmacy.getPhone()));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allPharmacies.size();
    }

    public static class PharmacyViewHolder extends RecyclerView.ViewHolder {
        private TextView pharmacyName;
        private ImageView goToButton;
        private TextView pharmacyAddress;
        private TextView pharmacyPhone;
        private TextView pharmacyClose;
        private ImageView getGoToButton2;

        public PharmacyViewHolder(@NonNull View itemView) {
            super(itemView);
            pharmacyName = itemView.findViewById(R.id.pharmacy_name);
            pharmacyAddress = itemView.findViewById(R.id.pharmacyAddress);
            pharmacyPhone = itemView.findViewById(R.id.pharmacyPhone);
            pharmacyClose = itemView.findViewById(R.id.pharmacyToWhere);
            goToButton = itemView.findViewById(R.id.goToButton);
            getGoToButton2 = itemView.findViewById(R.id.goToButton2);
        }
    }



}



