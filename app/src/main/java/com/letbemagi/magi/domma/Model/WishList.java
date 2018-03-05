package com.letbemagi.magi.domma.Model;

/**
 * Created by magi on 30/11/2017.
 */

public class WishList {
    private String nameWish,descWish;
    private int hargaWish;

    public WishList(String nameWish, String descWish, int hargaWish) {
        this.nameWish = nameWish;
        this.descWish = descWish;
        this.hargaWish = hargaWish;
    }

    public String getNameWish() {
        return nameWish;
    }

    public void setNameWish(String nameWish) {
        this.nameWish = nameWish;
    }

    public String getDescWish() {
        return descWish;
    }

    public void setDescWish(String descWish) {
        this.descWish = descWish;
    }

    public int getHargaWish() {
        return hargaWish;
    }

    public void setHargaWish(int hargaWish) {
        this.hargaWish = hargaWish;
    }
}
