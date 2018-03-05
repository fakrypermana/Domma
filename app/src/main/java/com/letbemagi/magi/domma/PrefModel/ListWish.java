package com.letbemagi.magi.domma.PrefModel;

import com.letbemagi.magi.domma.Model.WishList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magi on 03/12/2017.
 */

public class ListWish {
    List<WishList> listWish = new ArrayList<>();

    public List<WishList> getListWish() {
        return listWish;
    }

    public void setListWish(List<WishList> listWish) {
        this.listWish = listWish;
    }

    public ListWish(List<WishList> listWish) {

        this.listWish = listWish;
    }
}
