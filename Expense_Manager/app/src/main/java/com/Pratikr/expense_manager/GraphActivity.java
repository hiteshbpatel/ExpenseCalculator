package com.supriyalahade.expense_manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;

public class GraphActivity extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = (LineChart)findViewById(R.id.lineChart);

    }

}
