package com.android.ocr_digitalisation.ui.digitalisation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.ui.navigation.NavigationActivity;

public class DigitalisationPv extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitalisation_pv_drawer);
        this.configureBottomNavigationView(R.id.action_home);
    }
}