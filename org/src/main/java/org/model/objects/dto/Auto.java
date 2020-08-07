package org.model.objects.dto;

import java.io.Serializable;

public class Auto implements Serializable {

    private String marke;
    private Integer baujahr;
    private String beschreibung;
    private Integer id;

    public Auto(String marke, Integer baujahr, String beschreibung, Integer id ) {

        this.marke = marke;
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
