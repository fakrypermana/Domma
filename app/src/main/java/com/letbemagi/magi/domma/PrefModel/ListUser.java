package com.letbemagi.magi.domma.PrefModel;

import com.letbemagi.magi.domma.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magi on 05/12/2017.
 */

public class ListUser {
    List<User> listUser = new ArrayList<>();

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public ListUser(List<User> listUser) {

        this.listUser = listUser;
    }
}
