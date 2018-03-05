package com.letbemagi.magi.domma.PrefModel;

import com.letbemagi.magi.domma.Model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fakrypermana on 12/11/2017.
 */

public class ListCategory {
    List<Category> listCateg = new ArrayList<>();

    public ListCategory(List<Category> listCateg) {
        this.listCateg = listCateg;
    }

    public List<Category> getListCateg() {
        return listCateg;
    }

    public void setListCateg(List<Category> listCateg) {
        this.listCateg = listCateg;
    }
}
