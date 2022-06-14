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

public class DetailsPvFragment extends Fragment {

    private PvOcr pvOcr;
    private DigitalisationDetailsActivity activity;
    private DBHandler dbHandler = null;
    private RecensementRVAdapter recensementRVAdapter;
    private ArrayList<Recensement> recensementArrayList;
    private RecyclerView recyclerView;

    private DBManager dbManager;

    public DetailsPvFragment(){}

    public DetailsPvFragment(PvOcr pvOcr, DigitalisationDetailsActivity activity) {
        this.pvOcr = pvOcr;
        View view = activity.findViewById(R.id.content);
        this.activity = activity;
    }

    private View viewListRecensement(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws SQLException {
        final View view = (ViewGroup) layoutInflater.inflate(R.layout.fragment_details_pv, viewGroup, false);

        recensementArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());

        // recensementArrayList = dbHandler.readRecensement(getActivity());
        dbManager = new DBManager(getContext());
        Log.d("TEST --------", "tonga ato le zavatra");
        dbManager.open();
        recensementArrayList = dbManager.readRecensement();

        recensementRVAdapter = new RecensementRVAdapter(recensementArrayList, getActivity());
        recyclerView = view.findViewById(R.id.idRVRecensement);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recyclerView.setAdapter(recensementRVAdapter);

        return view;
    }

    /*
    private View viewListPv(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){
        final View view = (ViewGroup) layoutInflater.inflate(R.layout.fragment_details_pv, viewGroup, false);

        TextView nom_region = view.findViewById(R.id.nom_region);
        nom_region.setText(pvOcr.getNom());

        TextView pv_date_creation = view.findViewById(R.id.pv_date_creation);
        pv_date_creation.setText(pvOcr.getDatedecreation().toString());

        return view;
    }
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        try {
            view = viewListRecensement(inflater, container, savedInstanceState);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return view;
    }
}