package com.android.ocr_digitalisation.data.repository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.ocr_digitalisation.R;
import com.android.ocr_digitalisation.data.model.Recensement;
import com.android.ocr_digitalisation.service.DBManager;
import com.android.ocr_digitalisation.ui.digitalisation.fragment.PvFragment;

import java.util.ArrayList;

public class RecensementRVAdapter extends RecyclerView.Adapter {

    private ArrayList<Recensement> recensementArrayList;
    private Context context;
    private DBManager dbManager;

    public RecensementRVAdapter(ArrayList<Recensement> recensementArrayList, Context context) {
        this.recensementArrayList = recensementArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fichier_recensement_items, parent, false);
        Button btn = view.findViewById(R.id.detail_pv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView idTextView = (TextView) view.findViewById(R.id.card_candidat_fichier_recensement);
                String id_data = idTextView.getText().toString();
                Recensement tmp = dbManager.getRecensement(Long.parseLong(id_data));
                Log.d("Details :", tmp.getCin());

                Intent modify_intent = new Intent(context, PvFragment.class);
                modify_intent.putExtra("object", tmp);

                context.startActivity(modify_intent);
                ((Activity)context).finish();
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Recensement modal = recensementArrayList.get(position);
        // holder.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView idTextView = (TextView) v.findViewById(R.id.card_candidat_fichier_recensement);
                String id_data = idTextView.getText().toString();
                Recensement tmp = dbManager.getRecensement(Long.parseLong(id_data));
                Log.d("Details :", tmp.getCin());

                Intent modify_intent = new Intent(context, PvFragment.class);
                modify_intent.putExtra("object", tmp);

                context.startActivity(modify_intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recensementArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView fichierRecensement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            fichierRecensement = itemView.findViewById(R.id.nom_fichier_recensement);
        }
    }

}
