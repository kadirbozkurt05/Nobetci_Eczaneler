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
    private Singleton singleton;
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
        singleton = Singleton.getInstance();
         pharmacy = allPharmacies.get(position);

        // Set click listener on the item view
        holder.pharmacyName.setText(pharmacy.getName());
        if (pharmacy.getWhereToClose().isEmpty()){
            holder.pharmacyClose.setVisibility(View.GONE);
        }else{
            holder.pharmacyClose.setText("TARİF : "+pharmacy.getWhereToClose());
        }

        holder.pharmacyPhone.setText("TELEFON : "+pharmacy.getPhone());
        holder.pharmacyAddress.setText("ADRES : "+pharmacy.getAdress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pharmacy clickedPharmacy = allPharmacies.get(position);
                singleton.setSelectedPharmacy(clickedPharmacy);
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),PharmacyOnMap.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPharmacies.size();
    }

    public static class PharmacyViewHolder extends RecyclerView.ViewHolder {
        private TextView pharmacyName;
        private TextView pharmacyAddress;
        private TextView pharmacyPhone;
        private TextView pharmacyClose;

        public PharmacyViewHolder(@NonNull View itemView) {
            super(itemView);
            pharmacyName = itemView.findViewById(R.id.pharmacy_name);
            pharmacyAddress = itemView.findViewById(R.id.pharmacyAddress);
            pharmacyPhone = itemView.findViewById(R.id.pharmacyPhone);
            pharmacyClose = itemView.findViewById(R.id.pharmacyToWhere);

        }
    }



}



