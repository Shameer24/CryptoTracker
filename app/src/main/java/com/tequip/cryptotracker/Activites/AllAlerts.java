package com.tequip.cryptotracker.Activites;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tequip.cryptotracker.Adapter.AlertAdapter;
import com.tequip.cryptotracker.DBHandler;
import com.tequip.cryptotracker.Model.AlertModal;
import com.tequip.cryptotracker.R;

import java.util.ArrayList;

public class AllAlerts extends AppCompatActivity {
    DBHandler myDB;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager alertLayoutManager;
    RecyclerView.Adapter alertAdapter;
    ArrayList<AlertModal> alerts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allalerts_details);
        myDB = new DBHandler(AllAlerts.this);

        alerts = getAllAlerts();
        System.out.println("ALL ALERTS: "+alerts);
        recyclerView = (RecyclerView) findViewById(R.id.alertRecyclerView);

        alertLayoutManager = new LinearLayoutManager(this);
        alertAdapter = new AlertAdapter(alerts);
        recyclerView.setLayoutManager(alertLayoutManager);
        recyclerView.setAdapter(alertAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AllAlerts");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllAlerts.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }



    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            String coin_name = alerts.get(position).getCoin_name();
            deleteAlert(coin_name);
        }
    };

    public ArrayList getAllAlerts(){
        Cursor res = myDB.showAll();
        if(res.getCount()==0){
            Toast.makeText(AllAlerts.this,"No entries found", Toast.LENGTH_SHORT).show();
        }

        ArrayList<AlertModal> alerts = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            AlertModal alertModal = new AlertModal();
            alertModal.setCoin_name(res.getString(1));
            alertModal.setCoin_limit(res.getDouble(2));
            alerts.add(alertModal);
        }
        return (alerts);
    }

    public void deleteAlert(String coin_name){
        boolean res = myDB.deleteData(coin_name);
        if (res){
            Toast.makeText(AllAlerts.this,"Alert Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AllAlerts.this,"", Toast.LENGTH_SHORT).show();
        }
    }
}
