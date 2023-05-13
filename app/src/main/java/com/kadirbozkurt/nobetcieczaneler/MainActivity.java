package com.kadirbozkurt.nobetcieczaneler;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.kadirbozkurt.nobetcieczaneler.databinding.ActivityMainBinding;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;




    String province = "";
    String district = "";
    String[] iller = {"01 Adana", "02 Adıyaman", "03 Afyonkarahisar", "04 Ağrı", "05 Amasya", "06 Ankara", "07 Antalya", "08 Artvin",
            "09 Aydın", "10 Balıkesir", "11 Bilecik", "12 Bingöl", "13 Bitlis", "14 Bolu", "15 Burdur", "16 Bursa", "17 Çanakkale",
            "18 Çankırı", "19 Çorum", "20 Denizli", "21 Diyarbakır", "22 Edirne", "23 Elazığ", "24 Erzincan", "25 Erzurum", "26 Eskişehir",
            "27 Gaziantep", "28 Giresun", "29 Gümüşhane", "30 Hakkari", "31 Hatay", "32 Isparta", "33 Mersin", "34 İstanbul", "35 İzmir",
            "36 Kars", "37 Kastamonu", "38 Kayseri", "39 Kırklareli", "40 Kırşehir", "41 Kocaeli", "42 Konya", "43 Kütahya", "44 Malatya",
            "45 Manisa", "46 Kahramanmaraş", "47 Mardin", "48 Muğla", "49 Muş", "50 Nevşehir", "51 Niğde", "52 Ordu", "53 Rize", "54 Sakarya",
            "55 Samsun", "56 Siirt", "57 Sinop", "58 Sivas", "59 Tekirdağ", "60 Tokat", "61 Trabzon", "62 Tunceli", "63 Şanlıurfa", "64 Uşak",
            "65 Van", "66 Yozgat", "67 Zonguldak", "68 Aksaray", "69 Bayburt", "70 Karaman", "71 Kırıkkale", "72 Batman", "73 Şırnak",
            "74 Bartın", "75 Ardahan", "76 Iğdır", "77 Yalova", "78 Karabük", "79 Kilis", "80 Osmaniye", "81 Düzce"};



    String[] Adana = {"Aladağ", "Ceyhan", "Çukurova", "Feke", "İmamoğlu", "Karaisalı", "Karataş", "Kozan", "Pozantı", "Saimbeyli", "Sarıçam", "Seyhan", "Tufanbeyli", "Yumurtalık", "Yüreğir"};
    String[] Adıyaman = {"Merkez", "Besni", "Çelikhan", "Gerger", "Gölbaşı", "Kâhta", "Samsat", "Sincik", "Tut"};
    String[] Afyonkarahisar = {"Afyonkarahisar", "Başmakçı", "Bayat", "Bolvadin", "Çay", "Çobanlar", "Dazkırı", "Dinar", "Emirdağ", "Evciler", "Hocalar", "İhsaniye", "İscehisar", "Kızılören", "Sandıklı", "Sinanpaşa", "Sultandağı", "Şuhut"};
    String[] Ağrı = {"Merkez", "Diyadin", "Doğubayazıt", "Eleşkirt", "Hamur", "Patnos", "Taşlıçay", "Tutak"};
    String[] Aksaray = {"Ağaçören", "Aksaray", "Eskil", "Gülağaç", "Güzelyurt", "Ortaköy", "Sarıyahşi"};
    String[] Amasya = {"Merkez", "Göynücek", "Gümüşhacıköy", "Hamamözü", "Merzifon", "Suluova", "Taşova"};
    String[] Ankara = {"Akyurt", "Altındağ", "Ayaş", "Balâ", "Beypazarı", "Çamlıdere", "Çankaya", "Çubuk", "Elmadağ", "Etimesgut", "Evren", "Gölbaşı", "Güdül", "Haymana", "Kalecik", "Kahramankazan", "Keçiören", "Kızılcahamam", "Mamak", "Nallıhan", "Polatlı", "Pursaklar", "Sincan", "Şereflikoçhisar", "Yenimahalle"};
    String[] Antalya = {"Akseki", "Aksu", "Alanya", "Döşemealtı", "Elmalı", "Finike", "Gazipaşa", "Gündoğmuş", "İbradı", "Demre", "Kaş", "Kemer", "Kepez", "Konyaaltı", "Korkuteli", "Kumluca", "Manavgat", "Muratpaşa", "Serik"};
    String[] Ardahan = {"Merkez", "Çıldır", "Damal", "Göle", "Hanak", "Posof"};
    String[] Artvin = {"Ardanuç", "Arhavi", "Merkez", "Borçka", "Hopa", "Murgul", "Şavşat", "Yusufeli"};
    String[] Aydın = {"Bozdoğan", "Buharkent", "Çine", "Didim", "Efeler", "Germencik", "İncirliova", "Karacasu", "Karpuzlu", "Koçarlı", "Köşk", "Kuşadası", "Kuyucak", "Nazilli", "Söke", "Sultanhisar", "Yenipazar"};
    String[] Balıkesir = {"Merkez","Ayvalık", "Balya", "Bandırma", "Bigadiç", "Burhaniye", "Dursunbey", "Edremit", "Erdek", "Gömeç", "Gönen", "Havran", "İvrindi", "Kepsut", "Manyas", "Savaştepe", "Sındırgı", "Susurluk"};
    String[] Bartın = {"Amasra", "Merkez", "Kurucaşile", "Ulus"};
    String[] Batman = {"Merkez", "Beşiri", "Gercüş", "Hasankeyf", "Kozluk", "Sason"};
    String[] Bayburt = {"Aydıntepe", "Merkez", "Demirözü"};
    String[] Bilecik = {"Merkez", "Bozüyük", "Gölpazarı", "Osmaneli", "Pazaryeri", "Söğüt", "Yenipazar"};
    String[] Bingöl = {"Adaklı", "Merkez", "Genç", "Karlıova", "Kiğı", "Solhan", "Yayladere", "Yedisu"};
    String[] Bitlis = {"Adilcevaz", "Ahlat", "Merkez", "Güroymak", "Hizan", "Mutki", "Tatvan"};
    String[] Bolu = {"Merkez", "Dörtdivan", "Gerede", "Göynük", "Kıbrıscık", "Mengen", "Mudurnu", "Seben", "Yeniçağa"};
    String[] Burdur = {"Ağlasun", "Altınyayla", "Bucak", "Merkez", "Çavdır", "Çeltikçi", "Gölhisar", "Karamanlı", "Kemer", "Tefenni", "Yeşilova"};
    String[] Bursa = {"Büyükorhan", "Gemlik", "Gürsu", "Harmancık", "İnegöl", "İznik", "Karacabey", "Keles", "Kestel", "Mudanya", "Mustafakemalpaşa", "Nilüfer", "Orhaneli", "Orhangazi", "Osmangazi", "Yenişehir", "Yıldırım"};
    String[] Çanakkale = {"Ayvacık", "Bayramiç", "Biga", "Bozcaada", "Çan", "Merkez", "Eceabat", "Ezine", "Gelibolu", "Gökçeada", "Lapseki", "Yenice"};
    String[] Çankırı = {"Atkaracalar", "Bayramören", "Merkez", "Çerkeş", "Eldivan", "Ilgaz", "Kızılırmak", "Korgun", "Kurşunlu", "Orta", "Şabanözü", "Yapraklı"};
    String[] Çorum = {"Alaca", "Bayat", "Boğazkale", "Merkez", "Dodurga", "İskilip", "Kargı", "Laçin", "Mecitözü", "Oğuzlar", "Ortaköy", "Osmancık", "Sungurlu", "Uğurludağ"};
    String[] Denizli = {"Acıpayam", "Babadağ", "Baklan", "Bekilli", "Beyağaç", "Bozkurt", "Buldan", "Çal", "Çameli", "Çardak", "Çivril", "Güney", "Honaz", "Kale", "Merkezefendi", "Pamukkale", "Sarayköy", "Serinhisar", "Tavas"};
    String[] Diyarbakır = {"Bağlar", "Bismil", "Çermik", "Çınar", "Çüngüş", "Dicle", "Eğil", "Ergani", "Hani", "Hazro", "Kayapınar", "Kocaköy", "Kulp", "Lice", "Silvan", "Sur", "Yenişehir"};
    String[] Düzce = {"Akçakoca", "Cumayeri", "Çilimli", "Merkez", "Gölyaka", "Gümüşova", "Kaynaşlı", "Yığılca"};
    String[] Edirne = {"Enez", "Havsa", "İpsala", "Keşan", "Lalapaşa", "Meriç", "Merkez", "Süloğlu", "Uzunköprü"};
    String[] Elazığ = {"Ağın", "Alacakaya", "Arıcak", "Baskil", "Merkez", "Karakoçan", "Keban", "Kovancılar", "Maden", "Palu", "Sivrice"};
    String[] Erzincan = {"Çayırlı", "Merkez", "İliç", "Kemah", "Kemaliye", "Otlukbeli", "Refahiye", "Tercan", "Üzümlü"};
    String[] Erzurum = {"Aşkale", "Aziziye", "Çat", "Hınıs", "Horasan", "İspir", "Karaçoban", "Karayazı", "Köprüköy", "Narman", "Oltu", "Olur", "Palandöken", "Pasinler", "Pazaryolu", "Şenkaya", "Tekman", "Tortum", "Uzundere", "Yakutiye"};
    String[] Eskişehir = {"Alpu", "Beylikova", "Çifteler", "Günyüzü", "Han", "İnönü", "Mahmudiye", "Mihalgazi", "Mihalıççık", "Odunpazarı", "Sarıcakaya", "Seyitgazi", "Sivrihisar", "Tepebaşı"};
    String[] Gaziantep = {"Araban", "İslahiye", "Karkamış", "Nizip", "Nurdağı", "Oğuzeli", "Şahinbey", "Şehitkâmil", "Yavuzeli"};
    String[] Giresun = {"Alucra", "Bulancak", "Çamoluk", "Çanakçı", "Dereli", "Doğankent", "Espiye", "Eynesil", "Merkez", "Görele", "Güce", "Keşap", "Piraziz", "Şebinkarahisar", "Tirebolu", "Yağlıdere"};
    String[] Gümüşhane = {"Merkez", "Kelkit", "Köse", "Kürtün", "Şiran", "Torul"};
    String[] Hakkari = {"Çukurca", "Merkez", "Şemdinli", "Yüksekova"};
    String[] Hatay = {"Altınözü", "Antakya", "Arsuz", "Belen", "Defne", "Dörtyol", "Erzin", "Hassa", "İskenderun", "Kırıkhan", "Kumlu", "Payas", "Reyhanlı", "Samandağ", "Yayladağı"};
    String[] Isparta = {"Aksu", "Atabey", "Eğirdir", "Gelendost", "Gönen", "Isparta", "Keçiborlu", "Senirkent", "Sütçüler", "Şarkikaraağaç", "Uluborlu", "Yalvaç", "Yenişarbademli"};
    String[] Mersin = {"Akdeniz", "Anamur", "Aydıncık", "Bozyazı", "Çamlıyayla", "Erdemli", "Gülnar", "Mezitli", "Mut", "Silifke", "Tarsus", "Toroslar", "Yenişehir"};
    String[] İstanbul = {"Adalar", "Arnavutköy", "Ataşehir", "Avcılar", "Bağcılar", "Bahçelievler", "Bakırköy", "Başakşehir", "Bayrampaşa", "Beşiktaş", "Beykoz", "Beylikdüzü", "Beyoğlu", "Büyükçekmece", "Çatalca", "Çekmeköy", "Esenler", "Esenyurt", "Eyüp", "Fatih", "Gaziosmanpaşa", "Güngören", "Kadıköy", "Kağıthane", "Kartal", "Küçükçekmece", "Maltepe", "Pendik", "Sancaktepe", "Sarıyer", "Silivri", "Sultanbeyli", "Sultangazi", "Şile", "Şişli", "Tuzla", "Ümraniye", "Üsküdar", "Zeytinburnu"};
    String[] İzmir = {"Aliağa", "Balçova", "Bayındır", "Bayraklı", "Bergama", "Beydağ", "Bornova", "Buca", "Çeşme", "Çiğli", "Dikili", "Foça", "Gaziemir", "Güzelbahçe", "Karabağlar", "Karaburun", "Karşıyaka", "Kemalpaşa", "Kınık", "Kiraz", "Konak", "Menderes", "Menemen", "Narlıdere", "Ödemiş", "Seferihisar", "Selçuk", "Tire", "Torbalı", "Urla"};
    String[] Kars = {"Akyaka", "Arpaçay", "Digor", "Kağızman", "Merkez", "Sarıkamış", "Selim", "Susuz"};
    String[] Kastamonu = {"Abana", "Ağlı", "Araç", "Azdavay", "Bozkurt", "Cide", "Çatalzeytin", "Daday", "Devrekani", "Doğanyurt", "Hanönü", "İhsangazi", "İnebolu", "Merkez", "Küre", "Pınarbaşı", "Seydiler", "Şenpazar", "Taşköprü", "Tosya"};
    String[] Kayseri = {"Akkışla", "Bünyan", "Develi", "Felahiye", "Hacılar", "İncesu", "Kocasinan", "Melikgazi", "Özvatan", "Pınarbaşı", "Sarıoğlan", "Sarız", "Talas", "Tomarza", "Yahyalı", "Yeşilhisar"};
    String[] Kırklareli = {"Babaeski", "Demirköy", "Merkez", "Kofçaz", "Lüleburgaz", "Pehlivanköy", "Pınarhisar", "Vize"};
    String[] Kırşehir = {"Akçakent", "Akpınar", "Boztepe", "Çiçekdağı", "Kaman", "Merkez", "Mucur"};
    String[] Kocaeli = {"Başiskele", "Çayırova", "Darıca", "Derince", "Dilovası", "Gebze", "Gölcük", "İzmit", "Kandıra", "Karamürsel", "Kartepe", "Körfez"};
    String[] Konya = {"Ahırlı", "Akören", "Akşehir", "Altınekin", "Beyşehir", "Bozkır", "Cihanbeyli", "Çeltik", "Çumra", "Derbent", "Derebucak", "Doğanhisar", "Emirgazi", "Ereğli", "Güneysınır", "Hadım", "Halkapınar", "Hüyük", "Ilgın", "Kadınhanı", "Karapınar", "Karatay", "Kulu", "Meram", "Sarayönü", "Selçuklu", "Seydişehir", "Taşkent", "Tuzlukçu", "Yalıhüyük", "Yunak"};
    String[] Kütahya = {"Altıntaş", "Aslanapa", "Çavdarhisar", "Domaniç", "Dumlupınar", "Emet", "Gediz", "Hisarcık", "Merkez", "Pazarlar", "Şaphane", "Simav", "Tavşanlı"};
    String[] Malatya = {"Akçadağ", "Arapgir", "Arguvan", "Battalgazi", "Darende", "Doğanşehir", "Doğanyol", "Hekimhan", "Kale", "Kuluncak", "Pütürge", "Yazıhan", "Yeşilyurt"};
    String[] Manisa = {"Ahmetli", "Akhisar", "Alaşehir", "Demirci", "Gölmarmara", "Gördes", "Kırkağaç", "Köprübaşı", "Kula", "Salihli", "Sarıgöl", "Saruhanlı", "Selendi", "Soma", "Şehzadeler", "Turgutlu", "Yunusemre"};
    String[] Kahramanmaraş = {"Afşin", "Andırın", "Çağlayancerit", "Dulkadiroğlu", "Ekinözü", "Elbistan", "Göksun", "Nurhak", "Onikişubat", "Pazarcık", "Türkoğlu"};
    String[] Mardin = {"Artuklu", "Dargeçit", "Derik", "Kızıltepe", "Mazıdağı", "Midyat", "Nusaybin", "Ömerli", "Savur", "Yeşilli"};
    String[] Muğla = {"Bodrum", "Dalaman", "Datça", "Fethiye", "Kavaklıdere", "Köyceğiz", "Marmaris", "Menteşe", "Milas", "Ortaca", "Seydikemer", "Ula", "Yatağan"};
    String[] Muş = {"Bulanık", "Hasköy", "Korkut", "Malazgirt", "Merkez", "Varto"};
    String[] Nevşehir = {"Acıgöl", "Avanos", "Derinkuyu", "Gülşehir", "Hacıbektaş", "Kozaklı", "Merkez", "Ürgüp"};
    String[] Niğde = {"Altunhisar", "Bor", "Çamardı", "Çiftlik", "Merkez", "Ulukışla"};
    String[] Ordu = {"Akkuş", "Altınordu", "Aybastı", "Çamaş", "Çatalpınar", "Çaybaşı", "Fatsa", "Gölköy", "Gülyalı", "Gürgentepe", "İkizce", "Kabadüz", "Kabataş", "Korgan", "Kumru", "Mesudiye", "Perşembe", "Ulubey", "Ünye"};
    String[] Rize = {"Ardeşen", "Çamlıhemşin", "Çayeli", "Derepazarı", "Fındıklı", "Güneysu", "Hemşin", "İkizdere", "İyidere", "Kalkandere", "Pazar", "Merkez"};
    String[] Sakarya = {"Adapazarı", "Akyazı", "Arifiye","Merkez", "Ferizli", "Geyve", "Hendek", "Karasu", "Kaynarca", "Kocaali", "Pamukova", "Sapanca", "Serdivan", "Taraklı"};
    String[] Samsun = {"Alaçam", "Asarcık", "Atakum", "Ayvacık", "Bafra", "Canik", "Çarşamba", "Havza", "İlkadım", "Kavak", "Ladik", "Ondokuzmayıs", "Salıpazarı", "Tekkeköy", "Terme", "Vezirköprü", "Yakakent"};
    String[] Siirt = {"Merkez", "Tillo", "Baykan", "Eruh", "Kurtalan", "Pervari", "Şirvan"};
    String[] Sinop = {"Ayancık", "Boyabat", "Dikmen", "Durağan", "Erfelek", "Gerze", "Saraydüzü", "Merkez", "Türkeli"};
    String[] Sivas = {"Akıncılar", "Altınyayla", "Divriği", "Doğanşar", "Gemerek", "Gölova", "Hafik", "İmranlı", "Kangal", "Koyulhisar", "Merkez", "Suşehri", "Şarkışla", "Ulaş", "Yıldızeli", "Zara", "Gürün"};
    String[] Tekirdağ = {"Çerkezköy", "Çorlu", "Ergene", "Hayrabolu", "Kapaklı", "Malkara", "Marmara Ereğlisi", "Muratlı", "Saray", "Süleymanpaşa", "Şarköy"};
    String[] Tokat = {"Almus", "Artova", "Başçiftlik", "Erbaa", "Niksar", "Pazar", "Reşadiye", "Sulusaray", "Merkez", "Turhal", "Yeşilyurt", "Zile"};
    String[] Trabzon = {"Akçaabat", "Araklı", "Arsin", "Beşikdüzü", "Çarşıbaşı", "Çaykara", "Dernekpazarı", "Düzköy", "Hayrat", "Köprübaşı", "Maçka", "Of", "Ortahisar", "Sürmene", "Şalpazarı", "Tonya", "Vakfıkebir", "Yomra"};
    String[] Tunceli = {"Çemişgezek", "Hozat", "Mazgirt", "Nazımiye", "Ovacık", "Pertek", "Pülümür", "Merkez"};
    String[] Şanlıurfa = {"Akçakale", "Birecik", "Bozova", "Ceylanpınar", "Eyyübiye", "Halfeti", "Haliliye", "Harran", "Hilvan", "Karaköprü", "Siverek", "Suruç", "Viranşehir"};
    String[] Uşak = {"Banaz", "Eşme", "Karahallı", "Sivaslı", "Ulubey", "Merkez"};
    String[] Van = {"Bahçesaray", "Başkale", "Çaldıran", "Çatak", "Edremit", "Erciş", "Gevaş", "Gürpınar", "İpekyolu", "Muradiye", "Özalp", "Saray", "Tuşba"};
    String[] Yozgat = {"Akdağmadeni", "Aydıncık", "Boğazlıyan", "Çandır", "Çayıralan", "Çekerek", "Kadışehri", "Saraykent", "Sarıkaya", "Sorgun", "Şefaatli", "Yenifakılı", "Yerköy", "Merkez"};
    String[] Zonguldak = {"Alaplı", "Çaycuma", "Devrek", "Gökçebey", "Kilimli", "Kozlu", "Karadeniz Ereğli", "Merkez"};
    String[] Karaman = {"Ayrancı", "Başyayla", "Ermenek", "Merkez", "Kazımkarabekir", "Sarıveliler"};
    String[] Kırıkkale = {"Bahşılı", "Balışeyh", "Çelebi", "Delice", "Karakeçili", "Keskin", "Merkez", "Sulakyurt", "Yahşihan"};
    String[] Şırnak = {"Beytüşşebap", "Cizre", "Güçlükonak", "İdil", "Silopi", "Merkez", "Uludere"};
    String[] Iğdır = {"Aralık", "Merkez", "Karakoyunlu", "Tuzluca"};
    String[] Yalova = {"Altınova", "Armutlu", "Çınarcık", "Çiftlikköy", "Termal", "Merkez"};
    String[] Karabük = {"Eflani", "Eskipazar", "Merkez", "Ovacık", "Safranbolu", "Yenice"};
    String[] Kilis = {"Elbeyli", "Merkez", "Musabeyli", "Polateli"};
    String[] Osmaniye = {"Bahçe", "Düziçi", "Hasanbeyli", "Kadirli", "Merkez", "Sumbas", "Toprakkale"};


    String[][] wholeList = {Adana, Adıyaman, Afyonkarahisar, Ağrı, Amasya, Ankara, Antalya, Artvin,Aydın, Balıkesir,
            Bilecik, Bingöl, Bitlis, Bolu, Burdur, Bursa, Çanakkale,
            Çankırı, Çorum, Denizli, Diyarbakır, Edirne, Elazığ, Erzincan, Erzurum, Eskişehir,
            Gaziantep, Giresun, Gümüşhane, Hakkari, Hatay, Isparta, Mersin, İstanbul, İzmir,
            Kars, Kastamonu, Kayseri, Kırklareli, Kırşehir, Kocaeli, Konya, Kütahya, Malatya,
            Manisa, Kahramanmaraş, Mardin, Muğla, Muş, Nevşehir, Niğde, Ordu, Rize, Sakarya,
            Samsun, Siirt, Sinop, Sivas, Tekirdağ, Tokat, Trabzon, Tunceli, Şanlıurfa, Uşak,
            Van, Yozgat, Zonguldak, Aksaray, Bayburt, Karaman, Kırıkkale, Batman, Şırnak,
            Bartın, Ardahan, Iğdır, Yalova, Karabük, Kilis, Osmaniye, Düzce};

    ArrayAdapter districtArray;
    ArrayAdapter provinceArray;
    int provinceId;
    int districtId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        starBar();
        sharedPreferences =this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        provinceId=sharedPreferences.getInt("provinceId",0);
        districtId=sharedPreferences.getInt("districtId",0);
        spinnerProvince();
        spinnerDistrict();
        Utils.clickEffect(binding.button);
    }

    public void spinnerProvince(){
        //Creating the ArrayAdapter instance having the country list
        provinceArray = new ArrayAdapter(this,R.layout.items_list,iller);
        provinceArray.setDropDownViewResource(R.layout.items_list);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinnerProvince.setAdapter(provinceArray);

        binding.spinnerProvince.setSelection(provinceId);


        binding.spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedProvince = adapterView.getSelectedItem().toString();
                province = selectedProvince.substring(3);
                int provinceId = adapterView.getSelectedItemPosition();
                sharedPreferences.edit().putInt("provinceId",provinceId).commit();

                for (int j = 0; j < iller.length; j++) {
                    if (selectedProvince.equals(iller[i])){
                        districtArray = new ArrayAdapter<String>(MainActivity.this,
                                R.layout.items_list,
                                wholeList[i]);
                        binding.spinnerDistricts.setAdapter(districtArray);
                        binding.spinnerDistricts.setSelection(districtId);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    public void spinnerDistrict(){
        binding.spinnerDistricts.setSelection(districtId);
        binding.spinnerDistricts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedDistrict = adapterView.getSelectedItem().toString();
                district = selectedDistrict;
                districtId = adapterView.getSelectedItemPosition();
                sharedPreferences.edit().putInt("districtId",districtId).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = goToPharmacy(province,district);
                Intent intent = new Intent(MainActivity.this,ShowPharmacies.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }
    public void starBar(){
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                float rating = binding.ratingBar.getRating();
                if (rating < 3.5){
                    Snackbar.make(binding.getRoot(), "Uygulamadan memnun değil misin? Geri bildirim gönder.",Snackbar.LENGTH_INDEFINITE).setAction("TAMAM", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "kadirbozkurt005@gmail.com", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }else {

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this).setCancelable(false);
                    alert.setMessage("Bizi Play Store'da değerlendirmek ister misin?")
                            .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                            .show()   ;


                }
            }
        });
    }
    public String goToPharmacy(String province, String district){
       /* SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("province", province.toLowerCase());
        edit.putString("district",district.toLowerCase());
        edit.commit();

        */
        province = province.replaceAll("İ","i");
        district = district.replaceAll("İ","i");
        province = province.toLowerCase();

        district = district.toLowerCase();
        province =  province.replaceAll("ı","i")
                .replaceAll("ğ","g")
                .replaceAll("ş","s")
                .replaceAll("ö","o")
                .replaceAll("ü","u")
                .replaceAll(" ","-")
                .replaceAll("â","a")
                .replaceAll("ç","c");
        district =  district.replaceAll("ı","i")
                .replaceAll("ğ","g")
                .replaceAll("ş","s")
                .replaceAll("ö","o")
                .replaceAll("â","a")
                .replaceAll(" ","-")
                .replaceAll("ü","u")
                .replaceAll("ç","c");

        String url = "https://www.eczaneler.gen.tr/nobetci-"+province.toLowerCase()+"-"+district.toLowerCase();
        return url;

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this).setCancelable(false);
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
                .show()   ;
    }

}