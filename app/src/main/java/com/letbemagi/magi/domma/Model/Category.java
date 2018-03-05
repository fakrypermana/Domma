package com.letbemagi.magi.domma.Model;

/**
 * Created by fakrypermana on 12/11/2017.
 */

public class Category {
    String nameCateg;
    int typeCateg;

    public Category(String nameCateg, int typeCateg) {
        this.nameCateg = nameCateg;
        this.typeCateg = typeCateg;
    }

    public String getNameCateg() {
        return nameCateg;
    }

    public void setNameCateg(String nameCateg) {
        this.nameCateg = nameCateg;
    }

    public int getTypeCateg() {
        return typeCateg;
    }

    public void setTypeCateg(int typeCateg) {
        this.typeCateg = typeCateg;
    }
}
