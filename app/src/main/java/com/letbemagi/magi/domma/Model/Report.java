package com.letbemagi.magi.domma.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magi on 21/12/2017.
 */

public class Report {
    private String pengguna,nmDompet,keterangan;
    private int totalInc,totalOutc;

    public Report(String pengguna, String nmDompet, String keterangan, int totalInc, int totalOutc) {
        this.pengguna = pengguna;
        this.nmDompet = nmDompet;
        this.keterangan = keterangan;
        this.totalInc = totalInc;
        this.totalOutc = totalOutc;
    }

    public String getPengguna() {
        return pengguna;
    }

    public void setPengguna(String pengguna) {
        this.pengguna = pengguna;
    }

    public String getNmDompet() {
        return nmDompet;
    }

    public void setNmDompet(String nmDompet) {
        this.nmDompet = nmDompet;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getTotalInc() {
        return totalInc;
    }

    public void setTotalInc(int totalInc) {
        this.totalInc = totalInc;
    }

    public int getTotalOutc() {
        return totalOutc;
    }

    public void setTotalOutc(int totalOutc) {
        this.totalOutc = totalOutc;
    }
}
