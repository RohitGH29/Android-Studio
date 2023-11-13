package com.example.notes;

public class DataModel {

    String ai;
    String cn;

    public DataModel(){

    }

    public DataModel(String ai, String cn) {
        this.ai = ai;
        this.cn = cn;
    }

    public String getAi() {
        return ai;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }
}
