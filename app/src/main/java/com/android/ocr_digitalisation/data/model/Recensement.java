package com.android.ocr_digitalisation.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Recensement implements Parcelable {
    private String id;
    private String district;
    private String commune;
    private String fokontany;
    private String nom;
    private String prenom;
    private String datenaissance;
    private String lieunaissance;
    private String sexe;
    private String pere;
    private String mere;
    private String domicile;
    private String profession;
    private String cin;
    private String datecreation;
    private String lieucreation;
    private String numeroserie;

    public Recensement() {
    }

    public Recensement(String district, String commune, String fokontany, String nom, String prenom, String datenaissance, String lieunaissance, String sexe, String pere, String mere, String domicile, String profession, String cin, String datecreation, String lieucreation, String numeroserie) {
        this.district = district;
        this.commune = commune;
        this.fokontany = fokontany;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.lieunaissance = lieunaissance;
        this.sexe = sexe;
        this.pere = pere;
        this.mere = mere;
        this.domicile = domicile;
        this.profession = profession;
        this.cin = cin;
        this.datecreation = datecreation;
        this.lieucreation = lieucreation;
        this.numeroserie = numeroserie;
    }

    public Recensement(String id, String district, String commune, String fokontany, String nom, String prenom, String datenaissance, String lieunaissance, String sexe, String pere, String mere, String domicile, String profession, String cin, String datecreation, String lieucreation, String numeroserie) {
        this.id = id;
        this.district = district;
        this.commune = commune;
        this.fokontany = fokontany;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.lieunaissance = lieunaissance;
        this.sexe = sexe;
        this.pere = pere;
        this.mere = mere;
        this.domicile = domicile;
        this.profession = profession;
        this.cin = cin;
        this.datecreation = datecreation;
        this.lieucreation = lieucreation;
        this.numeroserie = numeroserie;
    }

    public String getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public String getCommune() {
        return commune;
    }

    public String getFokontany() {
        return fokontany;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public String getLieunaissance() {
        return lieunaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public String getPere() {
        return pere;
    }

    public String getMere() {
        return mere;
    }

    public String getDomicile() {
        return domicile;
    }

    public String getProfession() {
        return profession;
    }

    public String getCin() {
        return cin;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public String getLieucreation() {
        return lieucreation;
    }

    public String getNumeroserie() {
        return numeroserie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
