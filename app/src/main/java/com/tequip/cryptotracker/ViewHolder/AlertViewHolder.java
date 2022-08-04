package com.tequip.cryptotracker.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tequip.cryptotracker.R;

public class AlertViewHolder extends RecyclerView.ViewHolder{
    public TextView name;
    public TextView limit;
    public AlertViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        limit = (TextView) itemView.findViewById(R.id.limit);
    }
}
