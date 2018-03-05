package com.letbemagi.magi.domma.Fragment;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.letbemagi.magi.domma.Model.ItemTransIncome;
import com.letbemagi.magi.domma.PrefModel.ListTrans;
import com.letbemagi.magi.domma.Preferences.PrefTrans;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    TextView tvSisaUang,tvTotalInc,tvTotalOutc;
    List<ItemTransIncome> listTransaction = new ArrayList<>();
    int totalTrans,totalInc,totalOutc;
    Button btnResetTrans;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvSisaUang = view.findViewById(R.id.tvTotalUang);
        btnResetTrans = view.findViewById(R.id.btnResetTrans);

        loadDataTrans();
        countTrans();

        tvSisaUang.setText(String.valueOf(totalTrans));

        btnResetTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefTrans.clearAll(getActivity().getApplicationContext());
            }
        });

        return view;
    }

    private void countTrans(){
        for (int i = 0; i < listTransaction.size(); i++) {
            if(listTransaction.get(i).getType() == 0){
                //totalInc = totalInc + listTransaction.get(i).getMount();
                totalTrans = totalTrans + listTransaction.get(i).getMount();
            } else{
                //totalOutc = totalOutc + listTransaction.get(i).getMount();
                totalTrans = totalTrans - listTransaction.get(i).getMount();
            }
        }
    }

    private void loadDataTrans() {
        if (PrefTrans.load(getActivity().getApplicationContext()) != null) {
            ListTrans listTransact = PrefTrans.load(getActivity().getApplicationContext());
            Log.i("Magitest", "loadDataTrans: "+PrefTrans.getJSON(getActivity()));
            listTransaction.addAll(listTransact.getListTrans());
        }
    }

}
