package com.example.main1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class mpAndroidChart extends AppCompatActivity {
    private RadarChart radarChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        int i = 0;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mp_android_chart);
        radarChart = (RadarChart) findViewById(R.id.chart);
        ArrayList<RadarEntry> maxScore = new ArrayList<>();
        ArrayList<RadarEntry> averageScore = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Wake up Brain");
        String uid = user.getUid();
        String[] games = {"Match", "Response", "Sequence", "Verbal"};
        for(i = 0; i < games.length; i++){
            DatabaseReference game = mDatabaseRef.child("UserAccount").child(uid).child("gMap").child(games[i]);

            game.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        Game g = snapshot.getValue(Game.class);
                        Double score = g.getBest();
                        Double sum = g.getSum();
                        Integer times = g.getTimes();
                        Double avg;
                        if (times.equals(0)) {
                            avg = 0.0;

                        } else {
                            avg = sum / times;
                        }

                        maxScore.add(new RadarEntry((int) Math.round(score)));

                        averageScore.add(new RadarEntry((int) Math.round(avg)));
                        radarChart.notifyDataSetChanged();
                        radarChart.invalidate();


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }

        RadarDataSet maxdataSet = new RadarDataSet(maxScore,"bestScore");
        maxdataSet.setColor(Color.RED);
        maxdataSet.setFillColor(Color.rgb(200,0,0));
        maxdataSet.setDrawFilled(true);
        maxdataSet.setLineWidth(2f);
        maxdataSet.setDrawHighlightCircleEnabled(true);
        maxdataSet.setDrawHighlightIndicators(false);

        maxdataSet.setDrawHighlightCircleEnabled(true);
        maxdataSet.setHighLightColor(Color.rgb(255,0,0));


        RadarDataSet avgdataSet = new RadarDataSet(averageScore,"avgScore");
        avgdataSet.setColor(Color.BLUE);
        avgdataSet.setFillColor(Color.rgb(0,0,255));
        avgdataSet.setDrawFilled(true);
        avgdataSet.setLineWidth(2f);
        avgdataSet.setDrawHighlightCircleEnabled(true);
        avgdataSet.setDrawHighlightIndicators(false);
        avgdataSet.setDrawFilled(true);
        avgdataSet.setHighLightColor(Color.rgb(0,0,255));
        RadarData maxData = new RadarData();
        RadarData averageData = new RadarData();

        radarChart.setWebColor(Color.BLACK);
        radarChart.setWebColorInner(Color.BLACK);
        radarChart.setBackgroundColor(Color.WHITE);

        //x축라벨
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10);


        maxData.addDataSet(maxdataSet);
        maxData.setValueTextSize(15);


        averageData.addDataSet(avgdataSet);
        averageData.setValueTextSize(15);



        //game이 4개이므로 label도 4개 설정
        String[] labels = {"MATCH","RESPONSE","SEQUENCE","VERBAL"};
        ArrayList<IRadarDataSet> sets =new ArrayList<>();
        sets.add(maxdataSet);
        sets.add(avgdataSet);
        YAxis yAxis = radarChart.getYAxis();
        xAxis.setXOffset(0);
        yAxis.setYOffset(0);
        xAxis.setAxisMinimum(0);
        yAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(100);
        yAxis.setAxisMaximum(100);
        yAxis.setLabelCount(5,true);


        yAxis.setTextColor(Color.WHITE);
        yAxis.setEnabled(false);
        yAxis.setTextSize(0);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        RadarData fdata =new RadarData(sets);
        radarChart.setData(fdata);

        radarChart.animateXY(1000,1000);

    }
}
