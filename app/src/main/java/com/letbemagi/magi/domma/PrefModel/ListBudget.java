package com.letbemagi.magi.domma.PrefModel;

import com.letbemagi.magi.domma.Model.Budget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fakrypermana on 14/11/2017.
 */

public class ListBudget {
    List<Budget> listBudget = new ArrayList<>();

    public ListBudget(List<Budget> listBudget) {
        this.listBudget = listBudget;
    }

    public List<Budget> getListBudget() {
        return listBudget;
    }

    public void setListBudget(List<Budget> listBudget) {
        this.listBudget = listBudget;
    }
}
