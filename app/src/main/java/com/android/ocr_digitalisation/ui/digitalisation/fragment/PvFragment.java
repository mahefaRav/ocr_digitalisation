package com.android.ocr_digitalisation.ui.digitalisation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.data.model.PvOcr;
import com.android.ocr_digitalisation.data.model.Recensement;
import com.android.ocr_digitalisation.data.repository.RecensementRVAdapter;
import com.android.ocr_digitalisation.service.DBHandler;
import com.android.ocr_digitalisation.service.DBManager;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;

import java.sql.SQLException;
import java.util.ArrayList;

public class PvFragment extends Fragment {

    private PvOcr pvOcr;
    private DigitalisationDetailsActivity activity;
    private Bundle bundle;
    private TextView etat_civil_le, etat_civil_nom, etat_civil_prenom, etat_civil_naissance, etat_civil_lieu, etat_civil_cin_numero, etat_civil_domicile, etat_civil_district, etat_civil_commune, etat_civil_fokontany, etat_civil_serie_numero, etat_civil_fait, etat_civil_mere, etat_civil_pere, etat_civil_profession, etat_civil_sexe;

    public PvFragment() {
        // Required empty public constructor
    }

    public PvFragment(PvOcr pvOcr, DigitalisationDetailsActivity activity) {
        this.pvOcr = pvOcr;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        try {
            if (savedInstanceState != null){
                view = viewDetailsRecensement(inflater, container, savedInstanceState);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return view;
    }

    private View viewDetailsRecensement(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws SQLException {
        final View view = (ViewGroup) layoutInflater.inflate(R.layout.fragment_pv, viewGroup, false);

        Recensement tmp = null;

        tmp = bundle.getParcelable("object");

        /*
        if(requireActivity().getIntent().hasExtra("object")){
            tmp = (Recensement) requireActivity().getIntent().getSerializableExtra("object");
        }*/
        etat_civil_nom = view.findViewById(R.id.etat_civil_nom);
        etat_civil_prenom = view.findViewById(R.id.etat_civil_prenom);
        etat_civil_naissance = view.findViewById(R.id.etat_civil_naissance);
        etat_civil_lieu = view.findViewById(R.id.etat_civil_lieu);
        etat_civil_cin_numero = view.findViewById(R.id.etat_civil_cin_numero);
        etat_civil_domicile = view.findViewById(R.id.etat_civil_domicile);
        etat_civil_district = view.findViewById(R.id.etat_civil_district);
        etat_civil_commune = view.findViewById(R.id.etat_civil_commune);
        etat_civil_fokontany = view.findViewById(R.id.etat_civil_fokontany);
        etat_civil_serie_numero = view.findViewById(R.id.etat_civil_serie_numero);
        etat_civil_sexe = view.findViewById(R.id.etat_civil_sexe);
        etat_civil_pere = view.findViewById(R.id.etat_civil_pere);
        etat_civil_mere = view.findViewById(R.id.etat_civil_mere);
        etat_civil_profession = view.findViewById(R.id.etat_civil_profession);
        etat_civil_fait = view.findViewById(R.id.etat_civil_fait);
        etat_civil_le = view.findViewById(R.id.etat_civil_le);

        etat_civil_nom.setText(tmp.getNom());
        etat_civil_prenom.setText(tmp.getPrenom());
        etat_civil_naissance.setText(tmp.getDatenaissance());
        etat_civil_lieu.setText(tmp.getLieunaissance());
        etat_civil_cin_numero.setText(tmp.getCin());
        etat_civil_domicile.setText(tmp.getDomicile());
        etat_civil_district.setText(tmp.getDistrict());
        etat_civil_commune.setText(tmp.getCommune());
        etat_civil_fokontany.setText(tmp.getFokontany());
        etat_civil_serie_numero.setText(tmp.getNumeroserie());
        etat_civil_sexe.setText(tmp.getSexe());
        etat_civil_pere.setText(tmp.getPere());
        etat_civil_mere.setText(tmp.getMere());
        etat_civil_profession.setText(tmp.getProfession());
        etat_civil_fait.setText(tmp.getDatecreation());
        etat_civil_le.setText(tmp.getLieucreation());

        return view;
    }
}