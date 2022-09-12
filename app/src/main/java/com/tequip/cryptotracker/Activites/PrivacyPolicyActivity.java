package com.tequip.cryptotracker.Activites;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tequip.cryptotracker.R;
import com.google.android.material.navigation.NavigationView;

public class PrivacyPolicyActivity extends DrawerActivity {

    DrawerLayout drawerLayout;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://sites.google.com/view/wallpaperapp-privacypolicy/home");

        WebSettings webSettings =webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Privacy Policy");

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
            case R.id.nav_share_app:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, Wallpaper by TeQuip is an android app made by TeQuip Software Solutions that I use to personalize my phone with cool wallpapers. Get it for free at : https://play.google.com/store/apps/details?id=com.tequip.wallpaperapp");
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

    protected  void allocateActivityTitle(String titleString){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }

    }
}