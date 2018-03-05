package com.letbemagi.magi.domma.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letbemagi.magi.domma.Adapter.DompetAdapter;
import com.letbemagi.magi.domma.Model.Category;
import com.letbemagi.magi.domma.Model.Dompet;
import com.letbemagi.magi.domma.PrefModel.ListCategory;
import com.letbemagi.magi.domma.PrefModel.ListDompet;
import com.letbemagi.magi.domma.Preferences.PrefCategory;
import com.letbemagi.magi.domma.Preferences.PrefDompet;
import com.letbemagi.magi.domma.Preferences.PrefTrans;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fakrypermana on 08/11/2017.
 */

public class DompetFragment extends Fragment {

    EditText edtNamaDompet, edtDescDompet;
    Button btnAddDompet;
    RecyclerView rvDompet;
    DompetAdapter dompetAdapter;
    List<Dompet> listDompet = new ArrayList<>();

    public DompetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dompet, container, false);

        edtDescDompet = view.findViewById(R.id.edtDescDompet);
        edtNamaDompet = view.findViewById(R.id.edtNamaDompet);
        btnAddDompet = view.findViewById(R.id.btnAddDompet);
        rvDompet = view.findViewById(R.id.rvDompet);


        rvDompet.setLayoutManager(new LinearLayoutManager(getActivity()));
        dompetAdapter = new DompetAdapter(getActivity(), listDompet);
        rvDompet.setAdapter(dompetAdapter);

        loadDataDompet();
        //listDompet.add(new Dompet("Dompet Bisnis","Keperluan Bisnis"));

        btnAddDompet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dompet dompet = new Dompet(edtNamaDompet.getText().toString(), edtDescDompet.getText().toString());
                listDompet.add(dompet);
                String nameDompet = edtNamaDompet.getText().toString();
                String descDompet = edtDescDompet.getText().toString();
                // jika ada data
                if (nameDompet.trim().length() > 0 && descDompet.trim().length() > 0) {
                    if (PrefDompet.load(getActivity().getApplicationContext()) != null) {
                        ListDompet listDomp = PrefDompet.load(getActivity().getApplicationContext());

                        List<Dompet> list = listDomp.getListDompet();
                        list.add(dompet);
                        listDomp.setListDompet(list);

                        PrefDompet.save(listDomp, getActivity().getApplicationContext());
                    }
                    // kalau data masih kosong
                    else {
                        //Log.i("test", "onClick: data kosong");
                        List<Dompet> list = new ArrayList<>();
                        list.add(dompet);

                        PrefDompet.save(new ListDompet(list), getActivity().getApplicationContext());
                    }
                    Log.i("test", listDompet.size() + "");
                    dompetAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity().getApplicationContext(), "Dompet telah ditambahkan", Toast.LENGTH_SHORT).show();
                } else {

                    // user didn't entered username or password
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter name & desc dompet name",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
        return view;
    }

    private void loadDataDompet() {
        // jika ada data
        if (PrefDompet.load(getActivity().getApplicationContext()) != null) {
            ListDompet listDom = PrefDompet.load(getActivity().getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefDompet.getJSON(getActivity()));
            listDompet.addAll(listDom.getListDompet());
        }
    }
}












