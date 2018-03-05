package com.letbemagi.magi.domma.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.letbemagi.magi.domma.Adapter.CategoryAdapter;
import com.letbemagi.magi.domma.Adapter.ListIncomeAdapter;
import com.letbemagi.magi.domma.Model.Category;
import com.letbemagi.magi.domma.PrefModel.ListCategory;
import com.letbemagi.magi.domma.Preferences.PrefCategory;
import com.letbemagi.magi.domma.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by magi on 08/11/2017.
 */

public class CategoriesFragment extends Fragment{
    Button btnAddCateg;
    RadioButton btnRadCategIncome,btnRadCategOutcome;
    EditText edtNameCategory;
    RadioGroup rdCateg;
    RecyclerView rvCategoryInc,rvCategoryOutc;
    List<Category> listCateg = new ArrayList<>();
    List<Category> listCategInc = new ArrayList<>();
    List<Category> listCategOutc = new ArrayList<>();
    CategoryAdapter adapterIncome,adapterOutcome;


    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories,container,false);


        btnAddCateg = view.findViewById(R.id.btnAddCateg);
        btnRadCategIncome = view.findViewById(R.id.btnRadCategInc);
        btnRadCategOutcome = view.findViewById(R.id.btnRadCategOutc);
        edtNameCategory = view.findViewById(R.id.edtNamaCateg);
        rdCateg = view.findViewById(R.id.rdCategory);
        rvCategoryInc = view.findViewById(R.id.rvCategoryInc);
        rvCategoryOutc = view.findViewById(R.id.rvCategoryOutc);

        rvCategoryInc.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCategoryOutc.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapterIncome = new CategoryAdapter(getActivity(), listCategInc);
        adapterOutcome = new CategoryAdapter(getActivity(), listCategOutc);
        rvCategoryInc.setAdapter(adapterIncome);
        rvCategoryOutc.setAdapter(adapterOutcome);

        loadData();
        separateData(PrefCategory.load(getActivity().getApplicationContext()));

        /*listCategInc.add(new Category("Gaji Bulanan",0));
        listCategOutc.add(new Category("Makanan & Minuman",1));
        listCategOutc.add(new Category("Internet",1));
        listCategOutc.add(new Category("Listrik",1));*/

        btnAddCateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int type = 0;
                Category categ = new Category(edtNameCategory.getText().toString(), type);
                if(rdCateg.getCheckedRadioButtonId() == R.id.btnRadCategInc){
                    type = 0;
                } else if(rdCateg.getCheckedRadioButtonId() == R.id.btnRadCategOutc){
                    type = 1;
                }
                String nameCateg = edtNameCategory.getText().toString();
                if (nameCateg.trim().length() > 0) {
                    if (type == 0) {
                        categ.setTypeCateg(type);
                        listCategInc.add(categ);
                    } else {
                        categ.setTypeCateg(type);
                        listCategOutc.add(categ);
                    }

                    // jika ada data
                    if (PrefCategory.load(getActivity().getApplicationContext()) != null) {
                        ListCategory listCategory = PrefCategory.load(getActivity().getApplicationContext());

                        List<Category> list = listCategory.getListCateg();
                        list.add(categ);
                        listCategory.setListCateg(list);

                        PrefCategory.save(listCategory, getActivity().getApplicationContext());
                    }
                    // kalau data masih kosong
                    else {
                        //Log.i("fakritest", "onClick: data kosong");
                        List<Category> list = new ArrayList<>();
                        list.add(categ);

                        PrefCategory.save(new ListCategory(list), getActivity().getApplicationContext());
                    }
                    adapterIncome.notifyDataSetChanged();
                    adapterOutcome.notifyDataSetChanged();
                    Toast.makeText(getActivity().getApplicationContext(), "Kategori telah ditambahkan", Toast.LENGTH_SHORT).show();
                } else {

                    // user didn't entered username or password
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter category name",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
        return view;
    }

    private void loadData() {
        // jika ada data
        if (PrefCategory.load(getActivity().getApplicationContext()) != null){
            ListCategory listCategory = PrefCategory.load(getActivity().getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefCategory.getJSON(getActivity()));
            listCateg.addAll(listCategory.getListCateg());
        }
//        kalau data masih kosong
//        else {
//            listCateg = new ArrayList<>();
//        }
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
