package com.tequip.cryptotracker.Activites;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tequip.cryptotracker.R;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {

        drawerLayout =(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer,null);

        super.setContentView(drawerLayout);


        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(this,MainActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_aboutus:
                startActivity(new Intent(this,AboutUsActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_privacypolicy:
                startActivity(new Intent(this,PrivacyPolicyActivity.class));
                overridePendingTransition(0,0);
                break;
        }
        return false;
    }

    protected  void allocateActivityTitle(String titleString){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }

    }
}