package com.letbemagi.magi.domma.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.letbemagi.magi.domma.Model.Budget;
import com.letbemagi.magi.domma.Model.Category;
import com.letbemagi.magi.domma.PrefModel.ListBudget;
import com.letbemagi.magi.domma.PrefModel.ListCategory;
import com.letbemagi.magi.domma.Preferences.PrefBudget;
import com.letbemagi.magi.domma.Preferences.PrefCategory;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

public class InputTransOutcomeActivity extends AppCompatActivity {

    Button btnAddOutc;
    EditText edtMountTransOutc;
    Spinner spnOutc;
    int mountTransOutc;
    String spnCatOutc;
    private ArrayList<String> spinCategListOutc;
    List<Category> listCateg = new ArrayList<>();
    List<Category> listCategInc = new ArrayList<>();
    List<Category> listCategOutc = new ArrayList<>();
    List<Budget> listBudget = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_trans_outcome);

        btnAddOutc = findViewById(R.id.btnAddOutc);
        edtMountTransOutc = findViewById(R.id.edtMountTransOutc);
        spnOutc = findViewById(R.id.spinnerCategOutc);
        Toolbar mToolbar = findViewById(R.id.toolbarInputOutc);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Outcome");

        spinCategListOutc = new ArrayList<>();
        /*spinCategListOutc.add("Makanan & Minuman");
        spinCategListOutc.add("Listrik");
        spinCategListOutc.add("Belanja");*/

        ArrayAdapter<String> spinnerArrayAdapterOutc = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_spinner_item,spinCategListOutc);
        spinnerArrayAdapterOutc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOutc.setAdapter(spinnerArrayAdapterOutc);

        loadData();
        separateData(PrefCategory.load(getApplicationContext()));
        addToSpinner();
        spinnerArrayAdapterOutc.notifyDataSetChanged();
        loadDataBudget();

        btnAddOutc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mount = edtMountTransOutc.getText().toString();
                if (mount.trim().length() > 0) {
                    mountTransOutc = Integer.valueOf(edtMountTransOutc.getText().toString());
                    spnCatOutc = spnOutc.getSelectedItem().toString();
                    Intent intent = new Intent();
                    intent.putExtra("Jumlah", mountTransOutc);
                    intent.putExtra("Kategori", spnCatOutc);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter budget name",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private void addToSpinner() {
        for (int i = 0; i < listCategOutc.size(); i++) {
            spinCategListOutc.add(listCategOutc.get(i).getNameCateg());
        }
    }

    private void loadData() {
        // jika ada data
        if (PrefCategory.load(getApplicationContext()) != null){
            ListCategory listCategory = PrefCategory.load(getApplicationContext());
            //Log.i("Cobian", "loadData: "+PrefCategory.getJSON(getApplication()));
            listCateg = listCategory.getListCateg();
        }
        // kalau data masih kosong
        else {
            listCateg = new ArrayList<>();
        }
    }

    private void separateData(ListCategory load) {
        if (load != null){
            for (int i = 0; i < load.getListCateg().size(); i++) {
                if (load.getListCateg().get(i).getTypeCateg() == 0){
                    listCategInc.add(load.getListCateg().get(i));
                } else {
                    listCategOutc.add(load.getListCateg().get(i));
                }
            }
        }
    }

    private void loadDataBudget() {
        // jika ada data
        if (PrefBudget.load(getApplicationContext()) != null){
            ListBudget listBudg = PrefBudget.load(getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefDompet.getJSON(getActivity()));
            listBudget.addAll(listBudg.getListBudget());
        }
    }
}
