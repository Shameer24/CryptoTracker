package com.tequip.cryptotracker.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.tequip.cryptotracker.R;

public class CurrencyViewHolder extends RecyclerView.ViewHolder {
    public TextView price,currencyName,currencySymbol, currencyRate, oneHour, twentyfourHours, sevenDays;
    public ConstraintLayout constraintLayout;
    public CurrencyViewHolder(@NonNull View itemView) {
        super(itemView);
        currencyName = itemView.findViewById(R.id.currencyName);
        currencyRate = itemView.findViewById(R.id.currencyRate);
        currencySymbol = itemView.findViewById(R.id.currencySymbol);
        oneHour = itemView.findViewById(R.id.oneHour);
        twentyfourHours = itemView.findViewById(R.id.twentyfourHours);
        sevenDays = itemView.findViewById(R.id.sevenDays);

        constraintLayout=itemView.findViewById(R.id.carditem);
        price = itemView.findViewById(R.id.price);
    }

}
