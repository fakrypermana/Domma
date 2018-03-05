package com.letbemagi.magi.domma.Model;

/**
 * Created by magi on 30/11/2017.
 */

public class Dompet {
    private String nameDompet,descDompet,namaUser;

    public Dompet(String nameDompet, String descDompet) {
        this.nameDompet = nameDompet;
        this.descDompet = descDompet;
    }

    public String getNameDompet() {
        return nameDompet;
    }

    public void setNameDompet(String nameDompet) {
        this.nameDompet = nameDompet;
    }

    public String getDescDompet() {
        return descDompet;
    }

    public void setDescDompet(String descDompet) {
        this.descDompet = descDompet;
    }

}
