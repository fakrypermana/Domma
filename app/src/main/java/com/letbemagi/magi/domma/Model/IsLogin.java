package com.letbemagi.magi.domma.Model;

/**
 * Created by magi on 20/12/2017.
 */

public class IsLogin {

    private boolean isLogin;
    private String name;

    public IsLogin(boolean isLogin, String name) {
        this.isLogin = isLogin;
        this.name = name;
    }


    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
