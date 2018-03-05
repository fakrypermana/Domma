package com.letbemagi.magi.domma.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.letbemagi.magi.domma.Adapter.CategoryAdapter;
import com.letbemagi.magi.domma.Model.Category;
import com.letbemagi.magi.domma.PrefModel.ListCategory;
import com.letbemagi.magi.domma.Preferences.PrefCategory;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

public class InputTransIncomeActivity extends AppCompatActivity {
    Button btnAddInc;
    EditText edtMountTransInc;
    int mountTransInc;
    String spnCatInc;
    Spinner spnIncome;
    private ArrayList<String> spinCategListInc;
    List<Category> listCateg = new ArrayList<>();
    List<Category> listCategInc = new ArrayList<>();
    List<Category> listCategOutc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_trans_income);

        btnAddInc = findViewById(R.id.btnAddInc);
        edtMountTransInc = findViewById(R.id.edtMountTransInc);
        spnIncome = findViewById(R.id.spinerCategInc);

        Toolbar mToolbar = findViewById(R.id.toolbarInputInc);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Income");

        spinCategListInc = new ArrayList<>();
        //spinCategListInc.add("Uang Bulanan");


        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapterInc = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_spinner_item, spinCategListInc);

        loadData();
        separateData(PrefCategory.load(getApplicationContext()));
        addToSpinner();
        spinnerArrayAdapterInc.notifyDataSetChanged();

        spinnerArrayAdapterInc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIncome.setAdapter(spinnerArrayAdapterInc);

        /*spnIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long arg3)
            {

            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }
        });*/

        btnAddInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mount = edtMountTransInc.getText().toString();
                if (mount.trim().length() > 0) {
                    mountTransInc = Integer.valueOf(edtMountTransInc.getText().toString());
                    spnCatInc = spnIncome.getSelectedItem().toString();
                    Intent intent = new Intent();
                    intent.putExtra("Mount", mountTransInc);
                    intent.putExtra("Categories", spnCatInc);
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
        for (int i = 0; i < listCategInc.size(); i++) {
            spinCategListInc.add(listCategInc.get(i).getNameCateg());
        }
    }

    private void loadData() {
        // jika ada data
        if (PrefCategory.load(getApplicationContext()) != null) {
            ListCategory listCategory = PrefCategory.load(getApplicationContext());

            listCateg = listCategory.getListCateg();
        }
        // kalau data masih kosong
        else {
            listCateg = new ArrayList<>();
        }
    }

    private void separateData(ListCategory load) {
        if (load != null) {
            for (int i = 0; i < load.getListCateg().size(); i++) {
                if (load.getListCateg().get(i).getTypeCateg() == 0) {
                    listCategInc.add(load.getListCateg().get(i));
                } else {
                    listCategOutc.add(load.getListCateg().get(i));
                }
            }
        }
    }
}
