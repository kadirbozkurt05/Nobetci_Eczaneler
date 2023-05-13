package com.kadirbozkurt.nobetcieczaneler;

public class Singleton {
    private Pharmacy selectedPharmacy;
    private static Singleton singleton;

    private Singleton() {}

    public Pharmacy getSelectedPharmacy() {
        return selectedPharmacy;
    }

    public void setSelectedPharmacy(Pharmacy selectedPharmacy) {
        this.selectedPharmacy = selectedPharmacy;
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }

        return singleton;

    }
}
