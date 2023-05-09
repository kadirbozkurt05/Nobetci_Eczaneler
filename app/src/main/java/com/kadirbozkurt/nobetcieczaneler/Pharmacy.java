package com.kadirbozkurt.nobetcieczaneler;

public class Pharmacy {

    private String name;
    private String geoUrl;


    public Pharmacy(String name, String url, String adress, String phone, String whereToClose, String geoUrl) {
        this.name = name;
        this.url = url;
        this.adress = adress;
        this.phone = phone;
        this.whereToClose = whereToClose;
        this.geoUrl = geoUrl;

    }

    private String url;
    private String adress;
    private String phone;
    private String whereToClose;

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

    public String getGeoUrl() {
        return geoUrl;
    }

    public void setGeoUrl(String geoUrl) {
        this.geoUrl = geoUrl;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "name='" + name + '\'' +
                ", geoUrl='" + geoUrl + '\'' +
                ", url='" + url + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", whereToClose='" + whereToClose + '\'' +
                '}';
    }
}
