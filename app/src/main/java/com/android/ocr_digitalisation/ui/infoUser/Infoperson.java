package com.android.ocr_digitalisation.ui.infoUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.ui.navigation.NavigationActivity;

public class Infoperson extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureBottomNavigationView(R.id.action_info);
        setContentView(R.layout.activity_infoperson);
    }
}