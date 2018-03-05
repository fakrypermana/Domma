package com.letbemagi.magi.domma.PrefModel;

import com.letbemagi.magi.domma.Model.Dompet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magi on 03/12/2017.
 */

public class ListDompet {
    List<Dompet> listDompet = new ArrayList<>();

    public List<Dompet> getListDompet() {
        return listDompet;
    }

    public void setListDompet(List<Dompet> listDompet) {
        this.listDompet = listDompet;
    }

    public ListDompet(List<Dompet> listDompet) {

        this.listDompet = listDompet;
    }
}
