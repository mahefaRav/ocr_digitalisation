package com.android.ocr_digitalisation.ui.digitalisation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.data.model.PvOcr;
import com.android.ocr_digitalisation.data.model.Recensement;
import com.android.ocr_digitalisation.data.repository.DigitalisationRepository;
import com.android.ocr_digitalisation.service.DBManager;
import com.android.ocr_digitalisation.ui.digitalisation.fragment.DetailsPvFragment;
import com.android.ocr_digitalisation.ui.digitalisation.fragment.PvFragment;
import com.android.ocr_digitalisation.ui.login.LoginActivity;
import com.android.ocr_digitalisation.ui.navigation.NavigationActivity;
import com.android.ocr_digitalisation.utils.Note;

import java.sql.SQLException;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DigitalisationDetailsActivity extends NavigationActivity {

    public Bundle instance;
    private CardView content = null;
    private ProgressBar progressBar = null;
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private Button btnDetail;

    String[] from = new String[]{Note.ID, Note.NUMEROSERIE, Note.DATECREATION, Note.DISTRICT, Note.COMMUNE, Note.FOKONTANY, Note.NOM, Note.PRENOM, Note.DATENAISSANCE, Note.LIEUNAISSANCE, Note.SEXE, Note.PERE, Note.MERE, Note.DOMICILE, Note.PROFESSION, Note.CIN, Note.LIEUCREATION};
    final int[] to = new int[]{R.id.card_candidat_fichier_recensement, R.id.nom_fichier_recensement, R.id.date_fichier_recensement_creation};

    /*
    @Override
    public void onStart() {
        super.onStart();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTextView = (TextView) view.findViewById(R.id.card_candidat_fichier_recensement);
                String id_data = idTextView.getText().toString();
                Recensement tmp = dbManager.getRecensement(Long.parseLong(id_data));
                Log.d("Details :", tmp.getCin());
                Toast.makeText(getApplicationContext(), "selected Item CIN is " + tmp.getCin(), Toast.LENGTH_LONG).show();

                Intent modify_intent = new Intent(getApplicationContext(), PvFragment.class);
                modify_intent.putExtra("object", tmp);

                startActivity(modify_intent);
            }
        });
    }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIdPage(R.id.action_home);
        this.instance = savedInstanceState;
        setContentView(R.layout.activity_digitalisation_details_drawer);
        progressBar = findViewById(R.id.loading);
        try {
            progressBar.setVisibility(View.GONE);
            dbManager = new DBManager(this);
            dbManager.open();
            // to change cursor if doesn't work
            dbManager.readRecensement();
            // Curser used
            Cursor cursor = dbManager.fetch();

            listView = (ListView) findViewById(R.id.list_view);
            listView.setEmptyView(findViewById(R.id.empty));

            adapter = new SimpleCursorAdapter(this, R.layout.fichier_recensement_items, cursor, from, to, 0);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            Log.d("varification zavatra", "Avant setOnItemClickListener");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i(TAG, "Position" + position);

                    TextView idTextView = (TextView) findViewById(R.id.card_candidat_fichier_recensement);
                    String id_data = idTextView.getText().toString();

                    Toast.makeText(getApplicationContext(), "Test" + id_data, Toast.LENGTH_SHORT).show();
                    Recensement tmp = dbManager.getRecensement(Long.parseLong(id_data));

                    if (savedInstanceState != null) {
                        instance.putParcelable("object", tmp);
                    }

                    PvFragment pvFragment = new PvFragment();
                    pvFragment.setArguments(instance);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.card_trame, pvFragment).commit();
                }
            });
            Log.d("varification zavatra", "Apres setOnItemClickListener");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void viewAllRecensementList() throws SQLException {
        dbManager = new DBManager(this);
        dbManager.open();
        // to change cursor if doesn't work
        dbManager.readRecensement();
        // Curser used
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.fichier_recensement_items, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "Position" + position);

                TextView idTextView = (TextView) findViewById(R.id.card_candidat_fichier_recensement);
                String id_data = idTextView.getText().toString();
                Log.i("Details :", id_data);
                Toast.makeText(getApplicationContext(), "Test" + id_data, Toast.LENGTH_SHORT).show();
                Recensement tmp = dbManager.getRecensement(Long.parseLong(id_data));
                Intent modify_intent = new Intent(DigitalisationDetailsActivity.this, PvFragment.class);
                modify_intent.putExtra("object", tmp);

                startActivity(modify_intent);
            }
        });



        /*


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTextView = (TextView) view.findViewById(R.id.card_candidat_fichier_recensement);
                String id_data = idTextView.getText().toString();
                Recensement tmp = dbManager.getRecensement(Long.parseLong(id_data));
                Log.d("Details :", tmp.getCin());
                Toast.makeText(getApplicationContext(), "selected Item CIN is " + tmp.getCin(), Toast.LENGTH_LONG).show();

                Intent modify_intent = new Intent(getApplicationContext(), PvFragment.class);
                modify_intent.putExtra("object", tmp);

                startActivity(modify_intent);
            }
        });

        btnDetail = findViewById(R.id.detail_pv);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView idTextView = (TextView) findViewById(R.id.card_candidat_fichier_recensement);
                String id_data = idTextView.getText().toString();
                Recensement tmp = dbManager.getRecensement(Long.parseLong(id_data));
                Log.d("Details :", tmp.getCin());

                Intent modify_intent = new Intent(getApplicationContext(), PvFragment.class);
                modify_intent.putExtra("object", tmp);

                startActivity(modify_intent);
            }
        });

@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView idTextView = (TextView) view.findViewById(R.id.card_candidat_fichier_recensement);
        String id_data = idTextView.getText().toString();
        Recensement tmp = dbManager.getRecensement(Long.parseLong(id_data));
        Log.d("Details :", tmp.getCin());
        Log.d("Details :", tmp.getCin());
        Toast.makeText(getApplicationContext(), "selected Item CIN is " + tmp.getCin(), Toast.LENGTH_LONG).show();

        Intent modify_intent = new Intent(getApplicationContext(), PvFragment.class);
        modify_intent.putExtra("object", tmp);

        startActivity(modify_intent);
    }


         */

    }


}