package com.android.ocr_digitalisation.ui.digitalisation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.data.model.PvOcr;
import com.android.ocr_digitalisation.data.repository.DigitalisationRepository;
import com.android.ocr_digitalisation.ui.digitalisation.fragment.DetailsPvFragment;
import com.android.ocr_digitalisation.ui.login.LoginActivity;
import com.android.ocr_digitalisation.ui.navigation.NavigationActivity;

import java.util.List;

public class DigitalisationDetailsActivity extends NavigationActivity {

    public Bundle instance = null;
    private CardView content = null;
    private ProgressBar progressBar = null;
    private DigitalisationRepository digitalisationRepository = null;
    private List<PvOcr> listPVDigitalisation = null;
    DigitalisationDetailsActivity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIdPage(R.id.action_home);
        this.instance = savedInstanceState;
        setContentView(R.layout.activity_digitalisation_details_drawer);
        activity = this;
        content = this.findViewById(R.id.content);
        progressBar = findViewById(R.id.loading);

        digitalisationRepository = new DigitalisationRepository();
        progressBar.setVisibility(View.VISIBLE);
        content.post(new Runnable() {
            @Override
            public void run() {
                digitalisationRepository.getListPDF(activity);
            }
        });
    }

    public void getListPDDigital(final List<PvOcr> list){
        this.listPVDigitalisation = list;
        getPvDigitalisation(list);
    }

    public void getPvDigitalisation(List<PvOcr> listPV){
        content.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(getBaseContext());
        ScrollView scrollView = new ScrollView(getBaseContext());
        RelativeLayout.LayoutParams lpRelative = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(scrollView, lpRelative);

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lpLinear = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpLinear.topMargin = 50;
        scrollView.addView(linearLayout, lpLinear);

        int nombreElements = 0;
        if(listPV != null){
            nombreElements = listPV.size();
        }
        for (int i = 0; i < nombreElements; i++){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = 50;
            DetailsPvFragment detailsPvFragment = new DetailsPvFragment(listPVDigitalisation.get(i), activity);
            linearLayout.addView(detailsPvFragment.onCreateView(getLayoutInflater(),linearLayout,null),layoutParams);
        }
        progressBar.setVisibility(View.GONE);
    }

}