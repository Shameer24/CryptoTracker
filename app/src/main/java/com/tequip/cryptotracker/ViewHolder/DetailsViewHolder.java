package com.tequip.cryptotracker.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tequip.cryptotracker.R;

public class DetailsViewHolder extends RecyclerView.ViewHolder  {
    public TextView coinname,coinsymbol,coinslug;
    public TextView coindesc,techdoc,announce,chat,explorer,source,price;
    public ImageView logo,link,twitter,reddit;
    public Button setAlert;
    public DetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        logo = itemView.findViewById(R.id.logo);
        price = itemView.findViewById(R.id.price);
        coinname = itemView.findViewById(R.id.nameofcoin);
        coindesc = itemView.findViewById(R.id.coindesc);
        link = itemView.findViewById(R.id.link);
        twitter = itemView.findViewById(R.id.twitter);
        reddit = itemView.findViewById(R.id.reddit);
        setAlert = (Button) itemView.findViewById(R.id.setAlert);
//        techdoc = itemView.findViewById(R.id.techdoc);
//        announce = itemView.findViewById(R.id.announce);
//        chat = itemView.findViewById(R.id.chat);
//        explorer = itemView.findViewById(R.id.explorer);
//        source = itemView.findViewById(R.id.source);
    }


}
