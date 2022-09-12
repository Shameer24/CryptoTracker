package com.tequip.cryptotracker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tequip.cryptotracker.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tequip.cryptotracker.Model.AlertModal;
import com.tequip.cryptotracker.ViewHolder.AlertViewHolder;

import java.util.ArrayList;

public class AlertAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<AlertModal> alerts;
    public AlertAdapter(ArrayList<AlertModal> alerts) {
        this.alerts = alerts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alertitem,parent,false);
        return new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AlertModal alertModal = alerts.get(position);

        AlertViewHolder holderItem = (AlertViewHolder) holder;
        holderItem.name.setText(alertModal.getCoin_name());
        holderItem.limit.setText("$ "+String.valueOf(alertModal.getCoin_limit()));

    }

    @Override
    public int getItemCount() {
        return alerts.size();
    }

}
