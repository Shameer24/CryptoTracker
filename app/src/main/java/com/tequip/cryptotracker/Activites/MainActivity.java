package com.tequip.cryptotracker.Activites;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tequip.cryptotracker.Adapter.RVadapter;
import com.tequip.cryptotracker.DBHandler;
import com.tequip.cryptotracker.Interface.ILoadMore;
import com.tequip.cryptotracker.Model.CurrencyModal;

import com.tequip.cryptotracker.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class oData{
    public ArrayList<CurrencyModal> data;
}
public class MainActivity extends DrawerActivity {

    public ArrayList<CurrencyModal> currencyModalArrayList = new ArrayList<>();
    private RVadapter adapter;
    private RecyclerView recyclerView;
    private Button trendButton;
    private EditText search_bar;
    DBHandler myDB;
    private Button alertsButton;
    public Switch mode ;


    Handler handler = new Handler();
    Runnable runnable;
    int delay = 15*60000; //every 15 min

    Request request;
    RelativeLayout relativeLayout;
    OkHttpClient client;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        myDB = new DBHandler(this);

        //Explicit intent
        //Takes you to sister activity- Visualisation
        trendButton = findViewById(R.id.trendsButton);
        trendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(MainActivity.this, Visualization.class);
                startActivity(explicit);
            }
        });

        //Loads the first 30 coins into the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.RVcurrencies);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                loadFirst30coins(1);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();

        //search functionality
        search_bar = findViewById(R.id.search_bar);
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        //takes you to all alerts view
        alertsButton = findViewById(R.id.alertsButton);
        alertsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent alertPage = new Intent(MainActivity.this, AllAlerts.class);
                startActivity(alertPage);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title);
        setSupportActionBar(toolbar);
        title.setText("Home");
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_reload) {
                    onReloadPressed();
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.super_menu, menu);
        return true;
    }

    @Override
    //refer activity lifecycle https://developer.android.com/guide/components/activities/activity-lifecycle
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                onReloadPressed();
            }
        }, delay);
        super.onResume();
    }

    @Override
    //stop handler when activity not visible (shifted to visualisation activity) super.onPause();
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    //ACTION BAR
    //Code for functioning of the Reload button and change mode button in the Super Menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_reload:
                onReloadPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //reload main activity
    public void onReloadPressed(){
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);//Start the same Activity
        finish(); //finish Activity.
    }

    //SEARCH BAR
    //After text changed in search bar, creates a new ArrayList containing only matching Coin Names or Symbols
    private void filter(String text){
        ArrayList<CurrencyModal> filteredList = new ArrayList<>();
        for (CurrencyModal item: currencyModalArrayList){
            if (item.getCurrencyName().toLowerCase().contains(text.toLowerCase()) ||
                    item.getCurrencySymbol().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }



    private void setupAdapter(){
        adapter = new RVadapter(recyclerView, currencyModalArrayList,MainActivity.this);
        recyclerView.setAdapter(adapter);
        //25min
        adapter.setIloadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if(currencyModalArrayList.size()<=1000){
//                     loadNext30coins(currencyModalArrayList.size());
                }
                else{
                    Toast.makeText(MainActivity.this, "Max items is 1000", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //CRYPTO DATA LOADER
    //Calls the CoinMArketCapAPI and adds data to the CurrencyModalArrayList
    private void loadFirst30coins(int index){
        client = new OkHttpClient();
        request = new Request.Builder().url(
                String.format("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?start=%d&limit=30", index))
                .header("X-CMC_PRO_API_KEY", "c87b3b35-e0c3-412d-8c34-a641e9983e9c").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String body = response.body().string();
                Gson gson = new Gson();
                oData data1 = gson.fromJson(body, oData.class);
                final ArrayList<CurrencyModal> newCurrencyModalArrayList = data1.data;
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {
                        //newCurrencyModalArrayList.get(2).setCurrencySymbol("BB");
                        currencyModalArrayList.addAll(newCurrencyModalArrayList);
                        adapter.setLoaded();
                        adapter.updateData(newCurrencyModalArrayList);

                        //send notification if price goes below your alert
                        for(int coin = 0; coin<newCurrencyModalArrayList.size(); coin++){
                            String name = newCurrencyModalArrayList.get(coin).getCurrencyName();
                            double price = newCurrencyModalArrayList.get(coin).getQuote().getUSD().getPrice();
                            double limit = myDB.getLimit(name);
                            //System.out.println("Coin: "+name+" price: "+price+" limit: "+limit);
                            if(limit!=-1 && limit>=price){
                                System.out.println("***ALERT***"+name+" "+price+" "+limit);
                                sendNotification(name,price,limit);
                                myDB.deleteData(name);
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    //NOTIFICATION
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendNotification(String name, double price, double limit){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("mynotif","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        String msg=name.toUpperCase()+" Price dropped lower than "+limit;
        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this, "mynotif");
        builder.setContentTitle("PRICE DROPPED!!!");
        builder.setContentText(msg);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MainActivity.this);
        managerCompat.notify(1,builder.build());
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_aboutus:
                startActivity(new Intent(this, AboutUsActivity.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_privacypolicy:
                startActivity(new Intent(this, PrivacyPolicyActivity.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_share_app:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, Wallpaper by TeQuip is an android app made by TeQuip Software Solutions that I use to personalize my phone with cool wallpapers. Get it for free at : https://play.google.com/store/apps/details?id=com.tequip.cryptotracker");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.nav_rate_app:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;
        }
        return false;
    }

}