package com.android.ocr_digitalisation.ui.digitalisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.ui.login.LoginActivity;

public class DigitalisationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitalisation_details);
    }


}