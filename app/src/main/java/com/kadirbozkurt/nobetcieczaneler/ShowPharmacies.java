package com.kadirbozkurt.nobetcieczaneler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import com.kadirbozkurt.nobetcieczaneler.databinding.ActivityShowPharmaciesBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowPharmacies extends AppCompatActivity {
    ActivityShowPharmaciesBinding binding;
    private String url;
    private Element tableElement;
    private Elements allPharmaciesAsElement;
    private ArrayList<Pharmacy> allPharmacies;
    private String urlOfPharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowPharmaciesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        allPharmacies = new ArrayList<>();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        MyAsyncTask task = new MyAsyncTask();
        task.execute();


    }





    private class MyAsyncTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                // Parse the HTML document using Jsoup
                Document doc = Jsoup.connect(url).get();

                // Select the table element with the class name "table table-striped mt-2"
                Elements table = doc.select(".table.table-striped.mt-2");
                tableElement = table.get(1);

                // Get the tbody element within the table
                Element tbody = tableElement.select("tbody").first();

                // Get all the tr elements within the tbody
                allPharmaciesAsElement = tbody.select("tr");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer trCount) {

            for (Element each : allPharmaciesAsElement) {
                String adress = null;
                String whereToClose = null;
                double latitude = 0;
                double longitude = 0;

                urlOfPharmacy = "https://www.eczaneler.gen.tr" +  each.select("td").select("div").select("div").select("a").attr("href");
                String name = each.select("td").select("div").select("div").select("a").select("span").text();
                String adressAndWhereToClose = each.select("td").select("div").select("div").get(2).text();
                if(adressAndWhereToClose.contains("»")){
                    adress = adressAndWhereToClose.substring(0,adressAndWhereToClose.indexOf('»'));
                }else {
                    adress = adressAndWhereToClose;
                }
                int countOfDivForPhone = each.select("td").select("div").select("div").size();
                    String phone = each.select("td").select("div").select("div").get(countOfDivForPhone-1).text();


                if (adressAndWhereToClose.contains("»")){
                    whereToClose = adressAndWhereToClose.substring(adressAndWhereToClose.indexOf("»")+2);
                }else{
                    whereToClose = "";
                }

                Geocoder geocoder = new Geocoder(ShowPharmacies.this);
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocationName(adress, 1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (!addresses.isEmpty()) {
                    latitude = addresses.get(0).getLatitude();
                    longitude = addresses.get(0).getLongitude();
                    // Use latitude and longitude
                } else {
                    // Handle address not found
                }

                Pharmacy pharmacy = new Pharmacy(name,urlOfPharmacy,adress,phone,whereToClose,latitude,longitude);
                allPharmacies.add(pharmacy);

            }

            PharmacyAdapter adapter = new PharmacyAdapter(allPharmacies);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(ShowPharmacies.this));
            binding.recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }







}