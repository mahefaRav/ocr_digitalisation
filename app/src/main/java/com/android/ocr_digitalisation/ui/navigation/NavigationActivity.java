package com.android.ocr_digitalisation.ui.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationPv;
import com.android.ocr_digitalisation.ui.infoUser.Infoperson;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Bundle instance = null;
    private int idselected = 0;
    private int idPage = 0;

    public void setIdPage(int idPage) {
        this.idPage = idPage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = savedInstanceState;
    }

    @Override
    public void onStart(){
        super.onStart();
        configurationGlobal();
    }

    public void configurationGlobal(){
        Log.i("TONGA ATO","configurationGlobal");
        this.configureBottomNavigationView(idPage);
    }

    public void configureBottomNavigationView(final int id){

        final BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        if(navigationView != null){
            navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == id){
                        return true;
                    }

                    switch (item.getItemId()){
                        case R.id.action_add:
                            startActivity(new Intent(getApplicationContext(),DigitalisationPv.class));
                            overridePendingTransition(0,0);
                            break;

                        case R.id.action_home:
                            startActivity(new Intent(getApplicationContext(),DigitalisationDetailsActivity.class));
                            overridePendingTransition(0,0);
                            break;

                        case R.id.action_info:
                            startActivity(new Intent(getApplicationContext(),Infoperson.class));
                            overridePendingTransition(0,0);
                            break;
                    }
                    return false;
                }
            });
            if(id == R.id.action_home){
                navigationView.setSelectedItemId(R.id.action_home);
            }
            if(id == R.id.action_add){
                navigationView.setSelectedItemId(R.id.action_add);
            }
            if(id == R.id.action_info){
                navigationView.setSelectedItemId(R.id.action_info);
            }
        }
    }

}


