package com.example.demo21;

public class BazaATS {

    int id ;
    String tip, proizvoditil, model , nomer;

    public void setId(int id) {
        this.id = id;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setProizvoditil(String proizvoditil) {
        this.proizvoditil = proizvoditil;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public int getId() {
        return id;
    }

    public String getTip() {
        return tip;
    }

    public String getProizvoditil() {
        return proizvoditil;
    }

    public String getModel() {
        return model;
    }

    public String getNomer() {
        return nomer;
    }

    public BazaATS(int id, String tip, String proizvoditil, String model, String nomer) {
        this.id = id;
        this.tip = tip;
        this.proizvoditil = proizvoditil;
        this.model = model;
        this.nomer = nomer;
    }
}