package com.letbemagi.magi.domma.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.letbemagi.magi.domma.Adapter.BudgetAdapter;
import com.letbemagi.magi.domma.Model.Budget;
import com.letbemagi.magi.domma.Model.Category;
import com.letbemagi.magi.domma.PrefModel.ListBudget;
import com.letbemagi.magi.domma.PrefModel.ListCategory;
import com.letbemagi.magi.domma.Preferences.PrefBudget;
import com.letbemagi.magi.domma.Preferences.PrefCategory;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fakrypermana on 08/11/2017.
 */

public class BudgetFragment extends Fragment {
    Button btnAddBudget;
    EditText edtMount;
    Spinner spnBudget;
    RecyclerView rvBudget;
    BudgetAdapter adapterBudget;
    List<Budget> listBudget = new ArrayList<>();
    private ArrayList<String> spinBudget;
    List<Category> listCateg = new ArrayList<>();
    List<Category> listCategInc = new ArrayList<>();
    List<Category> listCategOutc = new ArrayList<>();

    public BudgetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        btnAddBudget = view.findViewById(R.id.btnAddBudget);
        edtMount = view.findViewById(R.id.edtMountBudget);
        spnBudget = view.findViewById(R.id.spnBudget);
        rvBudget = view.findViewById(R.id.rvBudget);

        //listBudget.add(new Budget("listrik",2000000));
        //listBudget.add(new Budget("Makanan dan Minuman", 1000000));
        rvBudget.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterBudget = new BudgetAdapter(getActivity(), listBudget);
        rvBudget.setAdapter(adapterBudget);

        spinBudget = new ArrayList<>();
        ArrayAdapter<String> spinnerArrayAdapterBudget = new ArrayAdapter<>(getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item,spinBudget);
        spinnerArrayAdapterBudget.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBudget.setAdapter(spinnerArrayAdapterBudget);

        loadData();
        loadDataBudget();
        separateData(PrefCategory.load(getActivity().getApplicationContext()));
        addToSpinner();
        spinnerArrayAdapterBudget.notifyDataSetChanged();

        btnAddBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (!edtMount.getText().toString().equals("")) {
                    Budget budget = new Budget(spnBudget.getSelectedItem().toString(), Integer.valueOf(
                            edtMount.getText().toString()));
                    listBudget.add(budget);
                    String nameBudget = spnBudget.getSelectedItem().toString();
                    String mount = edtMount.getText().toString();
                    // jika ada data
                if (nameBudget.trim().length() > 0 && mount.trim().length() > 0 ) {
                    if (PrefBudget.load(getActivity().getApplicationContext()) != null) {
                        ListBudget listBudg = PrefBudget.load(getActivity().getApplicationContext());

                        List<Budget> list = listBudg.getListBudget();
                        list.add(budget);
                        listBudg.setListBudget(list);

                        PrefBudget.save(listBudg, getActivity().getApplicationContext());
                    }
                    // kalau data masih kosong
                    else {
                        //Log.i("test", "onClick: data kosong");
                        List<Budget> list = new ArrayList<>();
                        list.add(budget);

                        PrefBudget.save(new ListBudget(list), getActivity().getApplicationContext());
                    }
//                    Log.i("test", listBudget.size()+"");
                    adapterBudget.notifyDataSetChanged();
                } else {

                    // user didn't entered username or password
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter budget name",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
        return view;
    }

    private void addToSpinner() {
        for (int i = 0; i < listCategOutc.size(); i++) {
            spinBudget.add(listCategOutc.get(i).getNameCateg());
        }
    }

    private void loadDataBudget() {
        // jika ada data
        if (PrefBudget.load(getActivity().getApplicationContext()) != null){
            ListBudget listBudg = PrefBudget.load(getActivity().getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefDompet.getJSON(getActivity()));
            listBudget.addAll(listBudg.getListBudget());
        }
    }

    private void loadData() {
        // jika ada data
        if (PrefCategory.load(getActivity().getApplicationContext()) != null){
            ListCategory listCategory = PrefCategory.load(getActivity().getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefCategory.getJSON(getActivity()));
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


}
