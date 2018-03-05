package com.letbemagi.magi.domma.Fragment;


import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RemoteViews;
import android.widget.Spinner;

import com.letbemagi.magi.domma.Activity.InputTransIncomeActivity;
import com.letbemagi.magi.domma.Activity.InputTransOutcomeActivity;
import com.letbemagi.magi.domma.Activity.LoginActivity;
import com.letbemagi.magi.domma.Adapter.ListIncomeAdapter;
import com.letbemagi.magi.domma.Model.Dompet;
import com.letbemagi.magi.domma.Model.ItemTransIncome;
import com.letbemagi.magi.domma.PrefModel.ListDompet;
import com.letbemagi.magi.domma.PrefModel.ListTrans;
import com.letbemagi.magi.domma.Preferences.PrefDompet;
import com.letbemagi.magi.domma.Preferences.PrefLogin;
import com.letbemagi.magi.domma.Preferences.PrefTrans;
import com.letbemagi.magi.domma.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.letbemagi.magi.domma.Preferences.PrefTrans.PREFS_NAME;

/**
 * Created by fakrypermana on 08/11/2017.
 */

public class TransactionFragment extends Fragment {
    List<ItemTransIncome> listTransaction = new ArrayList<>();
    ListIncomeAdapter adapterIncome;
    RecyclerView rvTransactionInc;
    Button btnAddTrans;
    RadioButton btnRadTransInc, btnRadTransOutc;
    List<Dompet> listDompet = new ArrayList<>();
    Spinner spinTrans;
    ArrayList<String> spnTrans;

    public TransactionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);

        rvTransactionInc = v.findViewById(R.id.rvTransactionInc);
        rvTransactionInc.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterIncome = new ListIncomeAdapter(getActivity(), listTransaction);
        rvTransactionInc.setAdapter(adapterIncome);
        spinTrans = v.findViewById(R.id.spnDompet);

        btnRadTransInc = v.findViewById(R.id.btnRadTransInc);
        btnRadTransOutc = v.findViewById(R.id.btnRadTransOutc);
        btnAddTrans = v.findViewById(R.id.btnAddTrans);

        spnTrans = new ArrayList<>();
        //spnTrans.add("Bisnis");
        ArrayAdapter<String> spinnerArrayAdapterTrans = new ArrayAdapter<>(getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item, spnTrans);
        spinnerArrayAdapterTrans.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTrans.setAdapter(spinnerArrayAdapterTrans);

        loadData();
        loadDataTrans();
        addToSpinner();
        spinnerArrayAdapterTrans.notifyDataSetChanged();

        btnAddTrans.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (btnRadTransInc.isChecked()) {
                    Intent i = new Intent(getActivity().getApplicationContext(), InputTransIncomeActivity.class);
                    startActivityForResult(i, 1);
                } else if (btnRadTransOutc.isChecked()) {
                    Intent i = new Intent(getActivity().getApplicationContext(), InputTransOutcomeActivity.class);
                    startActivityForResult(i, 2);
                }
            }
        });


        return v;
    }

    /*public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }*/


    private void loadDataTrans() {
        if (PrefTrans.load(getActivity().getApplicationContext()) != null) {
            ListTrans listTransact = PrefTrans.load(getActivity().getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefCategory.getJSON(getActivity()));
            listTransaction.addAll(listTransact.getListTrans());
        }
    }

    private void addToSpinner() {
        for (int i = 0; i < listDompet.size(); i++) {
            spnTrans.add(listDompet.get(i).getNameDompet());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy", Locale.US);
                Calendar c = Calendar.getInstance();
                //c.set(Calendar.HOUR_OF_DAY, 0);
                String date = dateFormatter.format(c.getTime());
                ItemTransIncome trans = new ItemTransIncome(
                        date, data.getStringExtra("Categories"), data.getIntExtra("Mount", 0), 0,
                        PrefLogin.load(getActivity()).getName(),spinTrans.getSelectedItem().toString());
                listTransaction.add(trans);
                if (PrefTrans.load(getActivity().getApplicationContext()) != null) {
                    ListTrans listTrans = PrefTrans.load(getActivity().getApplicationContext());

                    List<ItemTransIncome> list = listTrans.getListTrans();
                    list.add(trans);
                    listTrans.setListTrans(list);

                    PrefTrans.save(listTrans, getActivity().getApplicationContext());
                }
                // kalau data masih kosong
                else {
                    //Log.i("fakritest", "onClick: data kosong");
                    List<ItemTransIncome> list = new ArrayList<>();
                    list.add(trans);

                    PrefTrans.save(new ListTrans(list), getActivity().getApplicationContext());
                }
                adapterIncome.notifyDataSetChanged();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy", Locale.US);
                Calendar c = Calendar.getInstance();
                //c.set(Calendar.HOUR_OF_DAY, 0);
                String date = dateFormatter.format(c.getTime());
                ItemTransIncome trans = new ItemTransIncome(
                        date, data.getStringExtra("Kategori"), data.getIntExtra("Jumlah", 0), 1,
                        PrefLogin.load(getActivity()).getName(),spinTrans.getSelectedItem().toString());
                listTransaction.add(trans);
                if (PrefTrans.load(getActivity().getApplicationContext()) != null) {
                    ListTrans listTrans = PrefTrans.load(getActivity().getApplicationContext());

                    List<ItemTransIncome> list = listTrans.getListTrans();
                    list.add(trans);
                    listTrans.setListTrans(list);

                    PrefTrans.save(listTrans, getActivity().getApplicationContext());
                }
                // kalau data masih kosong
                else {
                    //Log.i("fakritest", "onClick: data kosong");
                    List<ItemTransIncome> list = new ArrayList<>();
                    list.add(trans);

                    PrefTrans.save(new ListTrans(list), getActivity().getApplicationContext());
                }
                adapterIncome.notifyDataSetChanged();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


    private void loadData() {
        // jika ada data
        if (PrefDompet.load(getActivity().getApplicationContext()) != null) {
            ListDompet listDom = PrefDompet.load(getActivity().getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefDompet.getJSON(getActivity()));
            listDompet.addAll(listDom.getListDompet());
        }
    }
}
