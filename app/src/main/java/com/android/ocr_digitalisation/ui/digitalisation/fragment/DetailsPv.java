package com.android.ocr_digitalisation.ui.digitalisation.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.data.model.PvOcr;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;

public class DetailsPv extends Fragment {

    private PvOcr pvOcr;
    private DigitalisationDetailsActivity activity;

    public DetailsPv(){}

    public DetailsPv(PvOcr pvOcr, DigitalisationDetailsActivity activity) {
        this.pvOcr = pvOcr;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = (ViewGroup) inflater.inflate(R.layout.fragment_details_pv, container, false);

        return view;
    }
}