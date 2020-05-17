package com.example.smartlockapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<Historique> {

    Context context;
    List<Historique>arrayListHistorique;




    public MyAdapter(@NonNull Context context, List<Historique> arrayListHistorique) {
        super ( context, R.layout.custum_list_item, arrayListHistorique );
        this.context= context;
        this.arrayListHistorique= arrayListHistorique;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.custum_list_item, null, true );
        TextView tvID = view.findViewById ( R.id.tx_id );
        TextView tvPseudo = view.findViewById ( R.id.txt_pseudo );


        tvID.setText ( arrayListHistorique.get(position).getId() );
        tvPseudo.setText ( arrayListHistorique.get ( position ).getPseudo () );

        return view;
    }
}
