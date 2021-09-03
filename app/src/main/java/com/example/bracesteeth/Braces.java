package com.example.bracesteeth;

public class Braces {
    private String _Id;
    private String _Date;
    private String _Prix;

    public Braces(String Id,String Date,String Prix){
        _Id = Id;
        _Date = Date;
        _Prix = Prix;
    }

    public String getId() {
        return _Id;
    }

    public void setId(String Id) {
        _Id = Id;
    }

    public String getDate() {
        return _Date;
    }

    public void setDate(String Date) {
        _Date = Date;
    }

    public String getPrix() {
        return _Prix;
    }

    public void setPrix(String Prix) {
        _Prix = Prix;
    }
}
