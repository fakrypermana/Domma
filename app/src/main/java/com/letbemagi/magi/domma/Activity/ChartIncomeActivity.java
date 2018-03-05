package com.letbemagi.magi.domma.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.letbemagi.magi.domma.Model.ItemTransIncome;
import com.letbemagi.magi.domma.PrefModel.ListTrans;
import com.letbemagi.magi.domma.Preferences.PrefTrans;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by magi on 04/12/2017.
 */

public class ChartIncomeActivity extends AppCompatActivity {

    List<ItemTransIncome> listTransaction = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_income);

        loadDataTrans();

        GraphView bar_graph = findViewById(R.id.gvIncome);
        for (int i = 0; i < listTransaction.size
                (); i++) {
            if (listTransaction.get(i).getType() == 0) {
                BarGraphSeries<DataPoint> bar_series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(0,0),
                        new DataPoint(i + 1, listTransaction.get(i).getMount())
                });


                bar_graph.addSeries(bar_series);

                // styling
                bar_series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });

                bar_series.setSpacing(50);

                // draw values on top
                bar_series.setDrawValuesOnTop(true);
                bar_series.setValuesOnTopColor(Color.RED);
                bar_series.setValuesOnTopSize(40);

            }
        }
    }


    private void loadDataTrans() {
        if (PrefTrans.load(getApplicationContext()) != null) {
            ListTrans listTransact = PrefTrans.load(getApplicationContext());
            //Log.i("Cobacoba", "loadData: "+PrefCategory.getJSON(getActivity()));
            listTransaction.addAll(listTransact.getListTrans());
        }
    }

}
