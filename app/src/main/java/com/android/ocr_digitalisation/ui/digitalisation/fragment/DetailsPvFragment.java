package com.android.ocr_digitalisation.ui.digitalisation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.TelephonyCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.data.model.PvOcr;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;

public class DetailsPvFragment extends Fragment {

    private PvOcr pvOcr;
    private DigitalisationDetailsActivity activity;

    public DetailsPvFragment(){}

    public DetailsPvFragment(PvOcr pvOcr, DigitalisationDetailsActivity activity) {
        this.pvOcr = pvOcr;
        View view = activity.findViewById(R.id.content);
        this.activity = activity;
    }

    private View viewListPv(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){
        final View view = (ViewGroup) layoutInflater.inflate(R.layout.fragment_details_pv, viewGroup, false);

        TextView nom_region = view.findViewById(R.id.nom_region);
        nom_region.setText(pvOcr.getNom());

        TextView pv_date_creation = view.findViewById(R.id.pv_date_creation);
        pv_date_creation.setText(pvOcr.getDatedecreation().toString());

        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return viewListPv(inflater, container, savedInstanceState);
    }
}