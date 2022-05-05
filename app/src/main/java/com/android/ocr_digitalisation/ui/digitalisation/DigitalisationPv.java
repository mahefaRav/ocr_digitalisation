package com.android.ocr_digitalisation.ui.digitalisation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.ui.navigation.NavigationActivity;

public class DigitalisationPv extends NavigationActivity {

    public Bundle instance = null;
    DigitalisationPv activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIdPage(R.id.action_add);
        this.instance = savedInstanceState;
        setContentView(R.layout.activity_digitalisation_pv_drawer);
        activity = this;
    }
}