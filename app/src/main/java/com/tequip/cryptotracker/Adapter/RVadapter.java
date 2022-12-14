package com.tequip.cryptotracker.Adapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tequip.cryptotracker.Activites.DetailsActivity;
import com.tequip.cryptotracker.ViewHolder.CurrencyViewHolder;
import com.tequip.cryptotracker.Interface.ILoadMore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.*;
import androidx.annotation.NonNull;

import com.tequip.cryptotracker.Model.CurrencyModal;
import com.tequip.cryptotracker.R;
import com.tequip.cryptotracker.Activites.SetAlert;

import java.util.ArrayList;

//set data from json to each item of the recycler view
public class RVadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

//    RecyclerView recyclerView;
    ArrayList<CurrencyModal> currencyModalArrayList;
    Activity activity;
    ILoadMore iloadMore;
    boolean isLoading;

    int visibleThreshold = 5, lastVisibleItem, totalItemCount;

    public void setIloadMore(ILoadMore iloadMore) {
        this.iloadMore = iloadMore;
    }

    public RVadapter(RecyclerView recyclerView, ArrayList<CurrencyModal> currencyModalArrayList, Activity activity) {
        this.currencyModalArrayList = currencyModalArrayList;
        this.activity = activity;
        //System.out.println("hi:"+currencyModalArrayList); // null

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem+ visibleThreshold)){
                    if(iloadMore!=null)
                        iloadMore.onLoadMore();
                    isLoading = true;
                }
            }
        });


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(
                R.layout.currencyitem, parent,false
        );
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CurrencyModal currencyModal = currencyModalArrayList.get(position);
        System.out.println(currencyModal);
        CurrencyViewHolder holderItem = (CurrencyViewHolder) holder;

        holderItem.currencyName.setText(currencyModal.getCurrencyName());
        holderItem.currencySymbol.setText(currencyModal.getCurrencySymbol());
        holderItem.currencyRate.setText(String.valueOf(currencyModal.getQuote().getUSD().getPrice()));
        holderItem.oneHour.setText(String.valueOf(currencyModal.getQuote().getUSD().getChange1h())+" %");
        holderItem.twentyfourHours.setText(String.valueOf(currencyModal.getQuote().getUSD().getChange24h())+" %");
        holderItem.sevenDays.setText(String.valueOf(currencyModal.getQuote().getUSD().getChange7d())+" %");

        System.out.println(String.valueOf(currencyModal.getQuote().getUSD().getMarket_cap()));

        holderItem.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailsActivity.class);

                Bundle bundle = new Bundle();
                intent.putExtra("pos",currencyModal);
                bundle.putInt("id",currencyModal.getId());
                bundle.putString("name",currencyModal.getCurrencyName());
                bundle.putDouble("price",currencyModal.getQuote().getUSD().getPrice());
                bundle.putDouble("change",currencyModal.getQuote().getUSD().getChange24h());
                bundle.putLong("market",currencyModal.getQuote().getUSD().getMarket_cap());
                bundle.putLong("volume",currencyModal.getQuote().getUSD().getVolume_24h());
                bundle.putLong("circ", currencyModal.getCirculating_supply());
                bundle.putLong("max",currencyModal.getMax_supply());

                intent.putExtras(bundle);

                activity.startActivity(intent);
            }
        });
        System.out.println(currencyModal);
        //button
//        holderItem.setAlert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println(currencyModal.getLowerLimit());
//                Intent explicit = new Intent(activity.getApplicationContext(), SetAlert.class);
//                explicit.putExtra("currency_modal", currencyModal); //passing the current currency object
//                activity.startActivity(explicit);
//            }
//        });
        holderItem.oneHour.setTextColor(String.valueOf(currencyModal.getQuote().getUSD().getChange1h()).contains("-")?
                Color.parseColor("#ed4b32"):Color.parseColor("#19b863"));
        holderItem.twentyfourHours.setTextColor(String.valueOf(currencyModal.getQuote().getUSD().getChange24h()).contains("-")?
                Color.parseColor("#ed4b32"):Color.parseColor("#19b863"));
        holderItem.sevenDays.setTextColor(String.valueOf(currencyModal.getQuote().getUSD().getChange7d()).contains("-")?
                Color.parseColor("#ed4b32"):Color.parseColor("#19b863"));
    }

    @Override
    public int getItemCount() {
        return currencyModalArrayList.size();
    }

    public void setLoaded(){isLoading=true;}

    public void updateData(ArrayList<CurrencyModal> currencyModals)
    {
        this.currencyModalArrayList = currencyModals;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<CurrencyModal> filteredList){
        this.currencyModalArrayList = filteredList;
        notifyDataSetChanged();
    }

}
