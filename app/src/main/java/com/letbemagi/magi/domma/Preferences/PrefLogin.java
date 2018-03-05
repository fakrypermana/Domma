package com.letbemagi.magi.domma.Preferences;

import android.content.Context;

import com.letbemagi.magi.domma.Model.IsLogin;
import com.letbemagi.magi.domma.PrefModel.ListUser;

/**
 * Created by magi on 20/12/2017.
 */

public class PrefLogin {
    public static final String PREFS_NAME = "login_prefs";
    public static final String PREFS_VAL = "login_value";

    public static void save(IsLogin data, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        complexPreferences.putObject(PREFS_VAL, data);
        complexPreferences.commit();
    }

    public static IsLogin load(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        return complexPreferences.getObject(PREFS_VAL, IsLogin.class);
    }

    public static String getJSON(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        return complexPreferences.getJSON(PREFS_VAL);
    }

    public static void clearAll(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }
}
