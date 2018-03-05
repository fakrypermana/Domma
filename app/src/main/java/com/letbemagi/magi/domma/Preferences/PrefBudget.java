package com.letbemagi.magi.domma.Preferences;

import android.content.Context;

import com.letbemagi.magi.domma.PrefModel.ListBudget;
import com.letbemagi.magi.domma.PrefModel.ListCategory;

/**
 * Created by fakrypermana on 14/11/2017.
 */

public class PrefBudget {
    public static final String PREFS_NAME = "budget_prefs";
    public static final String PREFS_VAL = "budget_value";

    public static void save(ListBudget data, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        complexPreferences.putObject(PREFS_VAL, data);
        complexPreferences.commit();
    }

    public static ListBudget load(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        return complexPreferences.getObject(PREFS_VAL, ListBudget.class);
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
