package com.android.ocr_digitalisation.ui.digitalisation;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.service.DBHandler;
import com.android.ocr_digitalisation.service.DBManager;
import com.android.ocr_digitalisation.ui.navigation.NavigationActivity;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DigitalisationPv extends NavigationActivity {

    private EditText edt_fiche_District, edt_fiche_Commune, edt_fiche_Fokontany, edt_fiche_numerisation_nom, edt_fiche_numerisation_prenom, edt_fiche_numerisation_naissance, edt_fiche_numerisation_lieu_naissance, edt_fiche_numerisation_sex, edt_fiche_numerisation_pere, edt_fiche_numerisation_mere, edt_fiche_numerisation_domicile, edt_fiche_numerisation_profession, edt_fiche_numerisation_cin, edt_fiche_numerisation_le, edt_fiche_numerisation_faita, edt_fiche_numerisation_numero_de_serie;
    private Button addRecensementFiche;
    private DBHandler dbHandler;
    private DBManager dbManager;
    private Button scanButton;
    private Button cameraButton;
    private Button mediaButton;
    private ImageView scannedImageView;
    private DatePickerDialog picker;
    private String spnDistrict, spnCommune, spnFokontany, spnSexe;

    Bitmap bitmap = null;

    int REQUEST_CODE = 99;
    int preference = ScanConstants.OPEN_CAMERA;
    public Bundle instance = null;
    DigitalisationPv activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIdPage(R.id.action_add);
        this.instance = savedInstanceState;
        setContentView(R.layout.activity_digitalisation_pv_drawer);
        activity = this;
        init();
        insertionRecensementFiles();
    }

    private void init() {
        scanButton = (Button) findViewById(R.id.fiche_validation_feuille_scanner);
        scanButton.setOnClickListener(new ScanButtonClickListener());
        cameraButton = (Button) findViewById(R.id.fiche_validation_feuille);
        cameraButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_CAMERA));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            mediaButton = (Button) findViewById(R.id.fiche_validation_feuille_media);
            mediaButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_MEDIA));
        }

        scannedImageView = (ImageView) findViewById(R.id.numerisation_feuille);
    }


    private class ScanButtonClickListener implements View.OnClickListener {

        private int preference;

        public ScanButtonClickListener(int preference) {
            this.preference = preference;
        }

        public ScanButtonClickListener() {
        }

        @Override
        public void onClick(View v) {
            startScan(preference);
        }
    }

    protected void startScan(int preference) {
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                getContentResolver().delete(uri, null, null);
                scannedImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap convertByteArrayToBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    protected void insertionRecensementFiles(){
        // dbHandler = new DBHandler(getApplicationContext());

        // Spinner element
        final Spinner spinnerDistrict = (Spinner) findViewById(R.id.fiche_District);
        final Spinner spinnerCommune = (Spinner) findViewById(R.id.fiche_Commune);
        final Spinner spinnerFokontany = (Spinner) findViewById(R.id.fiche_Fokontany);
        final Spinner spinnerSexe = (Spinner) findViewById(R.id.fiche_numerisation_sex);

        // Spinner Drop down elements
        List<String> categoriesDistrict = new ArrayList<String>();
        categoriesDistrict.add("District 1");
        categoriesDistrict.add("District 2");
        categoriesDistrict.add("District 3");
        categoriesDistrict.add("District 4");
        categoriesDistrict.add("District 5");
        categoriesDistrict.add("District 6");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterDistrict = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesDistrict);

        dataAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerDistrict.setAdapter(dataAdapterDistrict);

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                spnDistrict = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + spnDistrict, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Spinner Drop down elements
        List<String> categoriesCommune = new ArrayList<String>();
        categoriesCommune.add("Commune 1");
        categoriesCommune.add("Commune 2");
        categoriesCommune.add("Commune 3");
        categoriesCommune.add("Commune 4");
        categoriesCommune.add("Commune 5");
        categoriesCommune.add("Commune 6");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterCommune = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesCommune);

        dataAdapterCommune.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerCommune.setAdapter(dataAdapterCommune);

        spinnerCommune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                spnCommune = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + spnCommune, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Drop down elements
        List<String> categoriesFokontany = new ArrayList<String>();
        categoriesFokontany.add("Fokontany 1");
        categoriesFokontany.add("Fokontany 2");
        categoriesFokontany.add("Fokontany 3");
        categoriesFokontany.add("Fokontany 4");
        categoriesFokontany.add("Fokontany 5");
        categoriesFokontany.add("Fokontany 6");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterFokontany = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesFokontany);

        dataAdapterFokontany.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerFokontany.setAdapter(dataAdapterFokontany);

        spinnerFokontany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                spnFokontany = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + spnFokontany, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categoriesFokontany.add("Homme");
        categoriesFokontany.add("Femme");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerSexe.setAdapter(dataAdapter);

        spinnerSexe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                spnSexe = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + spnSexe, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // edt_fiche_District = findViewById(R.id.fiche_District);
        // edt_fiche_Commune = findViewById(R.id.fiche_Commune);
        // edt_fiche_Fokontany = findViewById(R.id.fiche_Fokontany);
        edt_fiche_numerisation_nom = findViewById(R.id.fiche_numerisation_nom);
        edt_fiche_numerisation_prenom = findViewById(R.id.fiche_numerisation_prenom);
        edt_fiche_numerisation_naissance = findViewById(R.id.fiche_numerisation_naissance);
        edt_fiche_numerisation_lieu_naissance = findViewById(R.id.fiche_numerisation_lieu_naissance);
        // edt_fiche_numerisation_sex = findViewById(R.id.fiche_numerisation_sex);
        edt_fiche_numerisation_pere = findViewById(R.id.fiche_numerisation_pere);
        edt_fiche_numerisation_mere = findViewById(R.id.fiche_numerisation_mere);
        edt_fiche_numerisation_domicile = findViewById(R.id.fiche_numerisation_domicile);
        edt_fiche_numerisation_profession = findViewById(R.id.fiche_numerisation_profession);
        edt_fiche_numerisation_cin = findViewById(R.id.fiche_numerisation_cin);
        edt_fiche_numerisation_le = findViewById(R.id.fiche_numerisation_le);
        edt_fiche_numerisation_faita = findViewById(R.id.fiche_numerisation_faita);
        edt_fiche_numerisation_numero_de_serie = findViewById(R.id.fiche_numerisation_numero_de_serie);
        addRecensementFiche = findViewById(R.id.fiche_validation_recensement);

        edt_fiche_numerisation_naissance.setInputType(InputType.TYPE_NULL);
        edt_fiche_numerisation_naissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DigitalisationPv.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edt_fiche_numerisation_naissance.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        edt_fiche_numerisation_le.setInputType(InputType.TYPE_NULL);
        edt_fiche_numerisation_le.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DigitalisationPv.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edt_fiche_numerisation_le.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        // dbHandler = new DBHandler(DigitalisationPv.this);
        dbManager = new DBManager(getApplicationContext());
        try {
            dbManager.open();
            addRecensementFiche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String district = spnDistrict;
                    String commune = spnCommune;
                    String fokontany = spnFokontany;
                    String nom = edt_fiche_numerisation_nom.getText().toString();
                    String prenom = edt_fiche_numerisation_prenom.getText().toString();
                    String datenaissance = edt_fiche_numerisation_naissance.getText().toString();
                    String lieunaissance = edt_fiche_numerisation_lieu_naissance.getText().toString();
                    String sexe = edt_fiche_numerisation_sex.getText().toString();
                    String pere = edt_fiche_numerisation_pere.getText().toString();
                    String mere = edt_fiche_numerisation_mere.getText().toString();
                    String domicile = edt_fiche_numerisation_domicile.getText().toString();
                    String profession = edt_fiche_numerisation_profession.getText().toString();
                    String cin = edt_fiche_numerisation_cin.getText().toString();
                    String datecreation = edt_fiche_numerisation_le.getText().toString();
                    String lieucreation = edt_fiche_numerisation_faita.getText().toString();
                    String numeroserie = edt_fiche_numerisation_numero_de_serie.getText().toString();

                    if (district.isEmpty() && commune.isEmpty() && fokontany.isEmpty() && nom.isEmpty() && prenom.isEmpty() && datenaissance.isEmpty() && lieunaissance.isEmpty() && sexe.isEmpty() && pere.isEmpty() && mere.isEmpty() && domicile.isEmpty() && profession.isEmpty() && cin.isEmpty() && datecreation.isEmpty() && lieucreation.isEmpty() && numeroserie.isEmpty()) {
                        Toast.makeText(DigitalisationPv.this, "Veuillez entrer toutes les données..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    dbManager.addFicheRecensement(district, commune, fokontany, nom, prenom, datenaissance, lieunaissance, sexe, pere, mere, domicile, profession, cin, datecreation, lieucreation, numeroserie);
                    // dbManager.addFicheRecensementTest();

                    Toast.makeText(DigitalisationPv.this, "Le fichier de recensement a été ajouté.", Toast.LENGTH_SHORT).show();
                    edt_fiche_District.setText("");
                    edt_fiche_Commune.setText("");
                    edt_fiche_Fokontany.setText("");
                    edt_fiche_numerisation_nom.setText("");
                    edt_fiche_numerisation_prenom.setText("");
                    edt_fiche_numerisation_naissance.setText("");
                    edt_fiche_numerisation_lieu_naissance.setText("");
                    edt_fiche_numerisation_sex.setText("");
                    edt_fiche_numerisation_pere.setText("");
                    edt_fiche_numerisation_mere.setText("");
                    edt_fiche_numerisation_domicile.setText("");
                    edt_fiche_numerisation_profession.setText("");
                    edt_fiche_numerisation_cin.setText("");
                    edt_fiche_numerisation_le.setText("");
                    edt_fiche_numerisation_faita.setText("");
                    edt_fiche_numerisation_numero_de_serie.setText("");
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                getContentResolver().delete(uri, null, null);
                scannedImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     */

    public void numerisationImage() {
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

}