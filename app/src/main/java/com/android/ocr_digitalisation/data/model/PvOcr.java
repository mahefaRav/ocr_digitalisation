package com.android.ocr_digitalisation.data.model;

import java.sql.Timestamp;

public class PvOcr {
    private int id;
    private String nom;
    private Timestamp datedecreation;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Timestamp getDatedecreation() {
        return datedecreation;
    }

    public PvOcr() {
    }

    public PvOcr(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public PvOcr(int id, String nom, Timestamp datedecreation) {
        this.id = id;
        this.nom = nom;
        this.datedecreation = datedecreation;
    }
}
