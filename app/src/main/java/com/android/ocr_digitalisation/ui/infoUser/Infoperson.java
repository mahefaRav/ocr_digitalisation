package com.android.ocr_digitalisation.ui.infoUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;
import com.android.ocr_digitalisation.ui.navigation.NavigationActivity;

public class Infoperson extends NavigationActivity {

    public Bundle instance = null;
    Infoperson activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIdPage(R.id.action_info);
        this.instance = savedInstanceState;
        setContentView(R.layout.activity_infoperson);
        activity = this;
    }
}