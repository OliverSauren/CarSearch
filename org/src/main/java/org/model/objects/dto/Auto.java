package org.model.objects.dto;

import java.io.Serializable;

public class Auto implements Serializable {

    private String marke;
    private String modell;
    private Integer baujahr;
    private String beschreibung;
    private Integer id;

    public Auto(String marke, String modell, Integer baujahr, String beschreibung, Integer id ) {

        this.marke = marke;
        this.modell = modell;
        this.baujahr = baujahr;
        this.beschreibung = beschreibung;
        this.id = id;

    }

    public Auto() {

    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public Integer getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(Integer baujahr) {
        this.baujahr = baujahr;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
