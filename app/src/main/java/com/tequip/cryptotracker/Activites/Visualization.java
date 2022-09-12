package com.tequip.cryptotracker.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tequip.cryptotracker.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class Visualization extends AppCompatActivity {
    private CandleStickChart candleStickChart;
    private Button apply;
    private RadioGroup coins;
    private RadioGroup time;
    private RadioButton coin_selected;
    private RadioButton time_selected;
    Request request;
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visual_details);

        candleStickChart = findViewById(R.id.candleStick);
        coins = findViewById(R.id.coins);
        time = findViewById(R.id.time);
        apply = findViewById(R.id.apply);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);
        title.setText("Trending Charts");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Visualization.this,MainActivity.class);
                startActivity(intent);
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int radio_coin_id = coins.getCheckedRadioButtonId();
                    coin_selected = findViewById(radio_coin_id);
                    int radio_time_id = time.getCheckedRadioButtonId();
                    time_selected = findViewById(radio_time_id);

                    String coin = coin_selected.getText().toString();
                    String time = time_selected.getText().toString();
                    System.out.println("COIN TIME "+coin+time);
                    showCandleStickChart(coin,time);
                } catch (Exception e) {
                    Toast.makeText(Visualization.this,"Select Both coin and time",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void showCandleStickChart(String coin, String time){
        final ArrayList<ArrayList<Long>> dataSet = new ArrayList<>();
        client = new OkHttpClient();
        String url = "https://api.gemini.com/v2/candles/"+ coin + "/"+ time;
        request = new Request.Builder().url(
                String.format(url)).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonString = response.body().string(); //array of arrays
                try {
                    //getting json array of array response in dataSet [time, open, high, low, close, volume]
                    JSONArray itemArray = new JSONArray(jsonString);
                    for (int i = 0; i < itemArray.length(); i++) {
                        ArrayList<Long> eachCoin = new ArrayList<>();
                        for(int j=0; j<itemArray.getJSONArray(i).length(); j++){
                            eachCoin.add(itemArray.getJSONArray(i).getLong(j));
                        }
                        dataSet.add(eachCoin);
                    }

                    //styling chart layout
                    YAxis yAxis = candleStickChart.getAxisLeft();
                    YAxis rightAxis = candleStickChart.getAxisRight();



                    yAxis.setDrawGridLines(false);
                    rightAxis.setDrawGridLines(false);
                    candleStickChart.requestDisallowInterceptTouchEvent(true);

                    XAxis xAxis = candleStickChart.getXAxis();
                    xAxis.setValueFormatter(new ValueFormatter() {
                        public String getFormattedValue(float value, AxisBase axis) {
                            return "date"; // yVal is a string array
                        }
                    });

                    xAxis.setDrawGridLines(false);// disable x axis grid lines
                    xAxis.setDrawLabels(true);
                    rightAxis.setTextColor(Color.BLACK);
                    yAxis.setDrawLabels(false);
                    xAxis.setGranularity(1f);
                    xAxis.setGranularityEnabled(true);
                    xAxis.setAvoidFirstLastClipping(true);

                    Legend l = candleStickChart.getLegend();
                    l.setEnabled(false);
                    candleStickChart.setHighlightPerDragEnabled(true);
                    candleStickChart.setDrawBorders(true);
                    candleStickChart.setBorderColor(getResources().getColor(R.color.purple_700));
                    String time_text;
                    switch(time){
                        case "1day":
                            time_text= "365 days";
                            candleStickChart.getDescription().setText("Variation of "+coin+" price over "+ time);
                            break;

                        case "1h":
                            time_text = "1500 hrs";
                            candleStickChart.getDescription().setText("Variation of "+coin+" price over "+ time);
                            break;

                        case "1m":
                            time_text = "1500 mins";
                            candleStickChart.getDescription().setText("Variation of "+coin+" price over "+ time);
                            break;
                    }

                    //setting data
                    ArrayList<String> xvalue = new ArrayList<String>();
                    ArrayList<CandleEntry> yvalue = new ArrayList<CandleEntry>();
                    for (int i=0; i<dataSet.size(); i++) {
                        yvalue.add(new CandleEntry(i, dataSet.get(i).get(2), dataSet.get(i).get(3), dataSet.get(i).get(1), dataSet.get(i).get(4)));
                    }

                    //styling data layout
                    CandleDataSet set1 = new CandleDataSet(yvalue,"");
                    set1.setColor(Color.rgb(80, 80, 80));
                    set1.setShadowColor(getResources().getColor(R.color.purple_200));
                    set1.setShadowWidth(0.8f);
                    set1.setDecreasingColor(getResources().getColor(R.color.red));
                    set1.setDecreasingPaintStyle(Paint.Style.FILL);
                    set1.setIncreasingColor(getResources().getColor(R.color.purple_200));
                    set1.setIncreasingPaintStyle(Paint.Style.FILL);
                    set1.setNeutralColor(Color.BLACK);
                    set1.setDrawValues(false);


                    //creating a data object with the dataset
                    CandleData data = new CandleData(set1);

                    //set data
                    candleStickChart.setData(data);
                    candleStickChart.invalidate();

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast. makeText(Visualization.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
