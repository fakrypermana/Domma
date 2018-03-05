package com.letbemagi.magi.domma.PrefModel;

import com.letbemagi.magi.domma.Model.ItemTransIncome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magi on 03/12/2017.
 */

public class ListTrans {
    List<ItemTransIncome> listTrans = new ArrayList<>();

    public ListTrans(List<ItemTransIncome> listTrans) {
        this.listTrans = listTrans;
    }

    public List<ItemTransIncome> getListTrans() {
        return listTrans;
    }

    public void setListTrans(List<ItemTransIncome> listTrans) {
        this.listTrans = listTrans;
    }
}
