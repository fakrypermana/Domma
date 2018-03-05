package com.letbemagi.magi.domma.Model;

/**
 * Created by fakrypermana on 10/11/2017.
 */

public class ItemTransIncome {
    String tanggal,kategori,name,dompet;
    int mount,type;


    public ItemTransIncome(String tanggal, String kategori, int mount, int type, String name, String dompet){
        this.tanggal = tanggal;
        this.kategori = kategori;
        this.mount = mount;
        this.type = type;
        this.name = name;
        this.dompet = dompet;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    public String getName() {
        return name;
    }

    public String getDompet() {
        return dompet;
    }

    public void setDompet(String dompet) {
        this.dompet = dompet;
    }

    public void setName(String name) {
        this.name = name;
    }
}
