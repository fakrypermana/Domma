package com.letbemagi.magi.domma.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.letbemagi.magi.domma.Model.Dompet;
import com.letbemagi.magi.domma.Model.ItemTransIncome;
import com.letbemagi.magi.domma.Model.User;
import com.letbemagi.magi.domma.PrefModel.ListDompet;
import com.letbemagi.magi.domma.PrefModel.ListTrans;
import com.letbemagi.magi.domma.PrefModel.ListUser;
import com.letbemagi.magi.domma.Preferences.PrefDompet;
import com.letbemagi.magi.domma.Preferences.PrefTrans;
import com.letbemagi.magi.domma.Preferences.PrefUser;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    TextView tvPengguna,tvDompet,tvKeterangan,tvReportPemasukan,tvReportPengeluaran;
    List<ItemTransIncome> listTrans = new ArrayList<>();
    int totalInc,totalOutc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        /*tvPengguna = findViewById(R.id.tvReportNamaPengguna);
        tvDompet = findViewById(R.id.tvReportNamaDompet);
        tvKeterangan = findViewById(R.id.tvReportKeterangan);*/
        tvReportPemasukan = findViewById(R.id.tvTotalIncome);
        tvReportPengeluaran = findViewById(R.id.tvTotalOutcome);
        /*rvReport = findViewById(R.id.rvReport);
        List<Report> listReport = new ArrayList<>();
        adapterReport = new ReportAdapter(getApplicationContext(),listReport);
        rvReport.setAdapter(adapterReport);*/

        loadDataTrans();
        countTrans();


        tvReportPemasukan.setText(String.valueOf(totalInc));
        tvReportPengeluaran.setText(String.valueOf(totalOutc));

    }

    private void countTrans(){
        for (int i = 0; i < listTrans.size(); i++) {
            if(listTrans.get(i).getType() == 0){
                totalInc = totalInc + listTrans.get(i).getMount();
                //totalTrans = totalTrans + listTrans.get(i).getMount();
            } else{
                totalOutc = totalOutc + listTrans.get(i).getMount();
                //totalTrans = totalTrans - listTrans.get(i).getMount();
            }
        }
    }


    private void loadDataTrans() {
        if (PrefTrans.load(getApplicationContext()) != null) {
            ListTrans listTransact = PrefTrans.load(getApplicationContext());
//            Log.i("Magitest", "loadDataTrans: "+PrefTrans.getJSON(getActivity()));
            listTrans.addAll(listTransact.getListTrans());
        }
    }



}
