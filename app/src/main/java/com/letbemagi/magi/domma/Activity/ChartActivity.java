package com.letbemagi.magi.domma.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.letbemagi.magi.domma.R;

/**
 * Created by magi on 30/11/2017.
 */

public class ChartActivity extends AppCompatActivity{

    TextView tvChartIncome,tvChartOutcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        tvChartIncome = findViewById(R.id.tvChartIncome);
        tvChartOutcome = findViewById(R.id.tvChartOutcome);

        tvChartIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChartIncomeActivity.class);
                startActivity(intent);
            }
        });

        tvChartOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChartOutcomeActivity.class);
                startActivity(intent);
            }
        });

    }

}
