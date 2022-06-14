package com.android.ocr_digitalisation.ui.infoUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.service.DBHandler;
import com.android.ocr_digitalisation.service.DBManager;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;
import com.android.ocr_digitalisation.ui.navigation.NavigationActivity;

import java.sql.SQLException;

public class Infoperson extends NavigationActivity {

    public Bundle instance = null;
    Infoperson activity = null;
    private DBManager dbManager;
    private DBHandler dbHandler;
    public TextView nombre_pv;
    public Button validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIdPage(R.id.action_info);
        this.instance = savedInstanceState;
        setContentView(R.layout.activity_infoperson);

        dbManager = new DBManager(getApplication());
        try {
            dbManager.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int nombre = dbManager.getRecensementsCount();
        nombre_pv = findViewById(R.id.pv_nombre);
        nombre_pv.setText(String.valueOf(nombre));
        Log.d("Nombre fichiers : ", String.valueOf(nombre));

        validation = findViewById(R.id.validation_pv);
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // vidage fichiers
                dbManager.censusFileDump();
            }
        });

        activity = this;
    }


}