package com.tequip.cryptotracker.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tequip.cryptotracker.Activites.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tequip.cryptotracker.Adapter.DetailAdapter;
import com.tequip.cryptotracker.Model.CurrencyModal;
import com.tequip.cryptotracker.Model.Root;
import com.tequip.cryptotracker.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DetailsActivity extends AppCompatActivity {

    private ArrayList<Root> detailModalArrayList = new ArrayList<>();
//    private ArrayList<Root> ppl2= new ArrayList<>();
//    public ArrayList currencyModalArrayList = new ArrayList();
    public CurrencyModal currencyModal;
    private DetailAdapter adapter;
    private RecyclerView recyclerView;
    Request request;
    RelativeLayout relativeLayout;
    OkHttpClient client;
    public TextView textView,prr;
    public Integer id;
    public TextView detailid;
    public String id1 = String.valueOf(id);
    String usd = getColoredSpanned("USD","#a5a5a5");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();

        Intent intent = getIntent();
        currencyModal = (CurrencyModal) intent.getSerializableExtra("pos");

        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        double price = bundle.getDouble("price");
        double changepercent = bundle.getDouble("change");
        long marketvalue = bundle.getLong("market");
        long volume = bundle.getLong("volume");
        long circular = bundle.getLong("circ");
        long max = bundle.getLong("max");

        String newprice = getColoredSpanned(String.valueOf(price),"#ffffff");

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);

        TextView prr = (TextView) drawerLayout.findViewById(R.id.price);
        prr.setText(Html.fromHtml(newprice+"  "+usd));

        TextView change = (TextView) drawerLayout.findViewById(R.id.change);
        change.setText((String.valueOf(changepercent)+" %").contains("-")? String.valueOf(changepercent)+" %"+" v":String.valueOf(changepercent)+" %"+" ^");
        change.setTextColor(String.valueOf(changepercent).contains("-")?
                Color.parseColor("#ed4b32"):Color.parseColor("#19b863"));

        TextView marketcap = drawerLayout.findViewById(R.id.marketcap);
        marketcap.setText("$ "+String.valueOf(marketvalue));

        TextView volumetext = drawerLayout.findViewById(R.id.volume);
        volumetext.setText("$ "+ String.valueOf(volume));

        TextView circ = drawerLayout.findViewById(R.id.circulating);
        circ.setText(String.valueOf(circular)+" BTC");

        TextView maxsup = drawerLayout.findViewById(R.id.maxsupply);
        maxsup.setText(String.valueOf(max)+" BTC");

        ImageView title = toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.detailrecycle);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                details(id);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        setupAdapter();

    }


    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    private void setupAdapter() {
        adapter = new DetailAdapter(recyclerView,detailModalArrayList, currencyModal, DetailsActivity.this);
        recyclerView.setAdapter(adapter);

    }


    private void details(int id) {
        client = new OkHttpClient();
        request = new Request.Builder().url(
                String.format("https://pro-api.coinmarketcap.com/v2/cryptocurrency/info?id=%d",id))
                .header("X-CMC_PRO_API_KEY", "c87b3b35-e0c3-412d-8c34-a641e9983e9c").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String body2 = response.body().string();
                Gson gson = new Gson();
//                Root data2 = gson.fromJson(body2,Root.class);
//                System.out.println(data2);
//                Gson gson1 = new Gson();
                ObjectMapper om = new ObjectMapper();
                Root root = om.readValue(body2, Root.class);
                System.out.println(root);
                List<Root> pp1 = Arrays.asList(om.readValue(body2, Root.class));
                final ArrayList<Root> ppl2 =new ArrayList<>(pp1);
//                final ArrayList<Root> newDetailModalArrayList = root.toArray(root.data._1);
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {
                        //newCurrencyModalArrayList.get(2).setCurrencySymbol("BB");
                        detailModalArrayList.addAll(ppl2);
                        System.out.println(detailModalArrayList);
                        adapter.setLoaded();
                        adapter.updateData(ppl2);

//                        for(int coin = 0; coin<newDetailModalArrayList.size(); coin++) {
//                            String symbol = newDetailModalArrayList.getName();
//                        }
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}