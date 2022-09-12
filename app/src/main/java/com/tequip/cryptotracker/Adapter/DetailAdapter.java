package com.tequip.cryptotracker.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tequip.cryptotracker.Activites.MainActivity;
import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tequip.cryptotracker.Activites.MainActivity;
import com.tequip.cryptotracker.Activites.SetAlert;
import com.tequip.cryptotracker.Interface.ILoadMore;
import com.tequip.cryptotracker.Model.CurrencyModal;
import com.tequip.cryptotracker.Model.Root;
import com.tequip.cryptotracker.R;
import com.tequip.cryptotracker.ViewHolder.DetailsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Root> detailModalArrayList;
    CurrencyModal currencyModal;
    Activity activity;
    ILoadMore iloadMore;
    boolean isLoading;
    private Context context;
    Integer index =0;


    int visibleThreshold = 0, lastVisibleItem, totalItemCount;

    public DetailAdapter(RecyclerView recyclerView, ArrayList<Root> detailModalArrayList, CurrencyModal currencyModal, Activity activity){
        this.activity=activity;
        this.detailModalArrayList = detailModalArrayList;
        this.currencyModal=currencyModal;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(
                R.layout.detail_layout, parent,false
        );
        context = activity;
        return new DetailsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Root detailModal = detailModalArrayList.get(position);

        DetailsViewHolder holderItem = (DetailsViewHolder) holder;


        String url = detailModal.getData().get_1().getLogo();
        Picasso.with(context).load(url).into(holderItem.logo);

        holderItem.coinname.setText(detailModal.getData().get_1().getName()+"("+detailModal.getData().get_1().getSymbol()+")");
        holderItem.coindesc.setText(detailModal.getData().get_1().getDescription());

//        CurrencyModal currencyModal = currencyModalArrayList.get(position);
        System.out.println(currencyModal);

        holderItem.setAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity.getApplicationContext(),SetAlert.class);
                i.putExtra("pos",currencyModal);
                activity.startActivity(i);
            }
        });

        ArrayList web = detailModal.getData().get_1().getUrls().getWebsite();
        if (index >= web.size() || index<0){
            holderItem.link.setVisibility(View.GONE);
        }
        else {
            String uri = detailModal.getData().get_1().getUrls().getWebsite().get(0).toString();

            holderItem.link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webpage = Uri.parse(uri);
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(intent);

                }
            });
        }

        ArrayList twitt = detailModal.getData().get_1().getUrls().getTwitter();
        if (index >= twitt.size() || index<0){
            holderItem.twitter.setVisibility(View.GONE);
        }
        else {
            String twitteruri = detailModal.getData().get_1().getUrls().getTwitter().get(0).toString();
            holderItem.twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webpage = Uri.parse(twitteruri);
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(intent);
                }
            });
        }

        ArrayList redd = detailModal.getData().get_1().getUrls().getReddit();
        if (index >= redd.size() || index<0){
            holderItem.reddit.setVisibility(View.GONE);
        }
        else {
            String reddit = detailModal.getData().get_1().getUrls().getReddit().get(0).toString();

            holderItem.reddit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webpage = Uri.parse(reddit);
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(intent);

                }
            });
        }

//        ArrayList tech = detailModal.getData().get_1().getUrls().getTechnical_doc();
//        if (index >= tech.size() || index<0){
//            holderItem.techdoc.setVisibility(View.GONE);
//        }
//        else {
//            String techdoc = detailModal.getData().get_1().getUrls().getTechnical_doc().get(0).toString();
//
//            holderItem.techdoc.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Uri webpage = Uri.parse(techdoc);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    context.startActivity(intent);
//
//                }
//            });
//        }
//
//        ArrayList announ = detailModal.getData().get_1().getUrls().getAnnouncement();
//        if (index >= announ.size() || index<0){
//            holderItem.announce.setVisibility(View.GONE);
//        }
//        else {
//            String announce = detailModal.getData().get_1().getUrls().getAnnouncement().get(0).toString();
//            holderItem.announce.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Uri webpage = Uri.parse(announce);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    context.startActivity(intent);
//
//                }
//            });
//        }
//
//        ArrayList chat = detailModal.getData().get_1().getUrls().getChat();
//        if (index >= chat.size() || index<0){
//            holderItem.chat.setVisibility(View.GONE);
//        }
//        else {
//            String chatweb= detailModal.getData().get_1().getUrls().getChat().get(0).toString();
//
//            holderItem.chat.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Uri webpage = Uri.parse(chatweb);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    context.startActivity(intent);
//
//                }
//            });
//        }
//
//        ArrayList explore = detailModal.getData().get_1().getUrls().getExplorer();
//        if (index >= explore.size() || index<0){
//            holderItem.explorer.setVisibility(View.GONE);
//        }
//        else {
//            String explorerweb= detailModal.getData().get_1().getUrls().getExplorer().get(0).toString();
//
//            holderItem.explorer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Uri webpage = Uri.parse(explorerweb);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    context.startActivity(intent);
//
//                }
//            });
//        }
//
//        ArrayList source = detailModal.getData().get_1().getUrls().getSource_code();
//        if (index >= source.size() || index<0){
//            holderItem.source.setVisibility(View.GONE);
//        }
//        else {
//            String sourceweb= detailModal.getData().get_1().getUrls().getSource_code().get(0).toString();
//
//            holderItem.source.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Uri webpage = Uri.parse(sourceweb);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    context.startActivity(intent);
//
//                }
//            });
//        }


    }

    @Override
    public int getItemCount() {
        return detailModalArrayList.size();
    }



    public void setLoaded(){isLoading=true;}

    public void updateData(ArrayList<Root> currencyModals)
    {
        this.detailModalArrayList = currencyModals;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<Root> filteredList){
        this.detailModalArrayList = filteredList;
        notifyDataSetChanged();
    }

//    public static class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        public DetailsViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//        }
//
//        @Override
//        public void onClick(View view) {
//        }
//    }
}
