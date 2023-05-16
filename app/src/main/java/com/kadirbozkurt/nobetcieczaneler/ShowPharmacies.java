package com.kadirbozkurt.nobetcieczaneler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.kadirbozkurt.nobetcieczaneler.databinding.ActivityShowPharmaciesBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ShowPharmacies extends AppCompatActivity {
    ActivityShowPharmaciesBinding binding;
    private int count;
    private String url;
    private Element tableElement;
    private Elements allPharmaciesAsElement;
    private ArrayList<Pharmacy> allPharmacies;
    private String urlOfPharmacy;
    private Pharmacy pharmacy;
    private String name;
    private String address;
    private String phone;
    private String whereToClose;
    private double latitude;
    private double longitude;
    private String locationUrl;
    private String adressAndWhereToClose;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences =this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        count = sharedPreferences.getInt("count",0);
        int theme = sharedPreferences.getInt("theme",android.R.style.Theme);
        setTheme(theme);
        super.onCreate(savedInstanceState);
        binding = ActivityShowPharmaciesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Utils.clickEffect(binding.options);
        allPharmacies = new ArrayList<>();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        MyAsyncTask task = new MyAsyncTask();
        task.execute();

        checkUpdate();
    }

    public void goToSettings(View view) {
        sharedPreferences.edit().putBoolean("isChecked",false).apply();
        startActivity(new Intent(ShowPharmacies.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                // Parse the HTML document using Jsoup
                System.out.println("url = " + url);
                Document doc = Jsoup.connect(url).get();

                // Select the table element with the class name "table table-striped mt-2"
                Elements table = doc.select(".table.table-striped.mt-2");
                tableElement = table.get(1);

                // Get the tbody element within the table
                Element tbody = tableElement.select("tbody").first();

                // Get all the tr elements within the tbody
                allPharmaciesAsElement = tbody.select("tr");

                for (Element each : allPharmaciesAsElement) {
                    urlOfPharmacy = "https://www.eczaneler.gen.tr" + each.select("td").select("div").select("div").select("a").attr("href");
                    name = each.select("td").select("div").select("div").select("a").select("span").text();
                    adressAndWhereToClose = each.select("td").select("div").select("div").get(2).text();
                    if (adressAndWhereToClose.contains("»")) {
                        address = adressAndWhereToClose.substring(0, adressAndWhereToClose.indexOf('»'));
                    } else {
                        address = adressAndWhereToClose;
                    }
                    int countOfDivForPhone = each.select("td").select("div").select("div").size();
                    phone = each.select("td").select("div").select("div").get(countOfDivForPhone - 1).text();

                    //Burayı yukarı alabiliriz sanırım
                    if (adressAndWhereToClose.contains("»")) {
                        whereToClose = adressAndWhereToClose.substring(adressAndWhereToClose.indexOf("»") + 2);
                    } else {
                        whereToClose = "";
                    }

                    try {
                        Document doc2 = null;
                        doc2 = Jsoup.connect(urlOfPharmacy).get();
                        locationUrl = doc2.select(".d-flex.justify-content-center.mt-4").select("a").attr("href");

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    int startIndex = locationUrl.indexOf("=") + 1;
                    int endIndex = locationUrl.indexOf(",");
                    double latitude = Double.parseDouble(locationUrl.substring(startIndex, endIndex));

                    startIndex = endIndex + 1;
                    double longitude = Double.parseDouble(locationUrl.substring(startIndex));

                    // Select the table element with the class name "table table-striped mt-2"
                    pharmacy = new Pharmacy(name, urlOfPharmacy, address, phone, whereToClose, latitude, longitude, locationUrl);
                    allPharmacies.add(pharmacy);

                }

            } catch (IOException e) {
                Toast.makeText(ShowPharmacies.this, "Bir hata oluştu lütfen internet bağlantınızı kontrol edin\n" +
                        "Hatanın devam etmesi durumunda lütfen bizimle iletişime geçin.", Toast.LENGTH_SHORT).show();
                finish();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Integer trCount) {
            PharmacyAdapter adapter = new PharmacyAdapter(allPharmacies);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(ShowPharmacies.this));
            binding.recyclerView.setAdapter(adapter);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.options:
                // do something
                sharedPreferences.edit().putBoolean("isChecked",false).apply();
                startActivity(new Intent(ShowPharmacies.this,MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        System.out.println("count = " + count);
        count++;
        if (count>=6){
            AlertDialog.Builder alert = new AlertDialog.Builder(ShowPharmacies.this).setCancelable(false);
            alert.setMessage("Bizi Play Store'da değerlendirmek ister misin?")
                    .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Uri uriUrl = Uri.parse("https://play.google.com/store/apps/details?id=com.kadirbozkurt.nobetcieczaneler");
                            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                            startActivity(launchBrowser);
                        }
                    })
                    .show();
            count = 0;
        }else{
            finish();
        }
        sharedPreferences.edit().putInt("count",count).apply();
    }

    private void checkUpdate(){
        String versionCode = ""+BuildConfig.VERSION_CODE;

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("version").document("version").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.get("version")!=null&&!value.getData().get("version").equals(versionCode) && count>=5){
                    AlertDialog.Builder alert = new AlertDialog.Builder(ShowPharmacies.this);
                    alert.setMessage("Yeni bir güncelleme var!").setNegativeButton("Daha Sonra", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).setPositiveButton("YÜKLE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kadirbozkurt.nobetcieczaneler")));
                        }
                    }).show();
                }
            }

        });


    }
}