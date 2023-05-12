package com.kadirbozkurt.nobetcieczaneler;

public class Pharmacy {

    private String name;
    private double latitude;
    private double longitude;
    private String url;
    private String adress;
    private String phone;
    private String whereToClose;
    private String locationUrl;

    public Pharmacy(String name, String url, String adress, String phone, String whereToClose, double latitude, double longitude, String locationUrl) {
        this.name = name;
        this.url = url;
        this.adress = adress;
        this.phone = phone;
        this.whereToClose = whereToClose;
        this.latitude = latitude;
        this.longitude=longitude;
        this.locationUrl = locationUrl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhereToClose() {
        return whereToClose;
    }

    public void setWhereToClose(String whereToClose) {
        this.whereToClose = whereToClose;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", url='" + url + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", whereToClose='" + whereToClose + '\'' +
                ", locationUrl='" + locationUrl + '\'' +
                '}';
    }
}
