package com.android.ocr_digitalisation.ui.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;
import com.android.ocr_digitalisation.ui.infoUser.Infoperson;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Bundle instance = null;
    private int idselected=0;
    private int idPage =0;

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
        if(navigationView!=null) {
            navigationView.setPadding(0, 0, 0, 40);
            idselected = id;
            navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //  item.setIcon(R.drawable.circle);

                    // checkColor();
                    if (item.getItemId() == id) {
                        return true;
                    }

                    switch (item.getItemId()) {


                        case R.id.action_home:
                            Intent intent = new Intent(getBaseContext(), DigitalisationDetailsActivity.class);
                            startActivity(intent);
                            finish();
                            break;

                        case R.id.action_add:
                            // intent = new Intent(getBaseContext(), DigitalisationSearch.class);
                            // startActivity(intent);
                            Log.i("Message:","Miandry alo anh!");
                            finish();
                            break;


                        case R.id.action_info:
                            intent = new Intent(getBaseContext(), Infoperson.class);
                            startActivity(intent);
                            finish();
                            break;

                        default:
                            break;
                    }

                    return true;
                }
            });
            if (id == R.id.action_home) {
                navigationView.setSelectedItemId(R.id.action_home);
            }
        }

    }
}


