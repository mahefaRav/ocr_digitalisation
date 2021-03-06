package com.android.ocr_digitalisation.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationPv;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setOnclickListener();
    }

    private void setOnclickListener(){
        final Button loginButton=findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(getBaseContext(), DigitalisationPv.class));
                    finish();
                    // Intent intent = new Intent(LoginActivity.this, DigitalisationDetailsActivity.class);
                    // startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}