package com.letbemagi.magi.domma.Model;

/**
 * Created by fakrypermana on 14/11/2017.
 */

public class Budget {
    String nameBudget;
    int mountBudget;

    public Budget(String nameBudget, int mountBudget) {
        this.nameBudget = nameBudget;
        this.mountBudget = mountBudget;
    }

    public String getNameBudget() {
        return nameBudget;
    }

    public void setNameBudget(String nameBudget) {
        this.nameBudget = nameBudget;
    }

    public int getMountBudget() {
        return mountBudget;
    }

    public void setMountBudget(int mountBudget) {
        this.mountBudget = mountBudget;
    }
}
