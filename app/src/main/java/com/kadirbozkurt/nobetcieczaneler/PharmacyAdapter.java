package com.kadirbozkurt.nobetcieczaneler;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder> {
    private ArrayList<Pharmacy> allPharmacies;
    private static String geoUrl;
    private static String pharmacyUrl;
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
        pharmacyUrl = pharmacy.getUrl();

        GetLatLong getLatLong = new GetLatLong();
        getLatLong.execute(pharmacyUrl);

        System.out.println(pharmacy.toString());


        holder.goToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUrl));
                holder.itemView.getContext().startActivity(intent);
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

    private class GetLatLong extends AsyncTask<String,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {

            try {
                Document doc = Jsoup.connect(strings[0]).get();
                geoUrl = doc.select(".d-flex.justify-content-center.mt-4").select("a").attr("href");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            pharmacy.setGeoUrl(geoUrl);
        }
    }

}



