package com.example.appnhac.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appnhac.Model.RankBaihat;
import com.example.appnhac.R;

import java.util.ArrayList;

public class ListplaymusicAdapter extends RecyclerView.Adapter<ListplaymusicAdapter.ViewHolder> {
    Context context;
    ArrayList<RankBaihat> arrayListbaihat;

    public ListplaymusicAdapter(Context context, ArrayList<RankBaihat> arrayListbaihat) {
        this.context = context;
        this.arrayListbaihat = arrayListbaihat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_listsong_playmusic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tenbaihat.setText(arrayListbaihat.get(position).getTenbaihat());
        holder.casi.setText(arrayListbaihat.get(position).getCasi());
        holder.number.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return arrayListbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tenbaihat,casi,number;
        public ViewHolder(View itemView) {
            super(itemView);
            tenbaihat = itemView.findViewById(R.id.namesongplaymusic);
            casi = itemView.findViewById(R.id.singerplaymusic);
            number = itemView.findViewById(R.id.numbersongplaymusic);
        }
    }
}
