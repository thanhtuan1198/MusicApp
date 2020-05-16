package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachplaylistAdapter extends RecyclerView.Adapter<DanhsachplaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> danhsachplaylist;



    public DanhsachplaylistAdapter(Context context, ArrayList<Playlist> danhsachplaylist) {
        this.context = context;
        this.danhsachplaylist = danhsachplaylist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_danhsach_playlist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Playlist playlist = danhsachplaylist.get(position);
        Picasso.with(context).load(playlist.getIcon()).into(holder.hinhindanhsachplaylist);
        holder.tenalbum.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return danhsachplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView hinhindanhsachplaylist;
        TextView tenalbum;

        public ViewHolder(View itemView) {
            super(itemView);
            hinhindanhsachplaylist = itemView.findViewById(R.id.imgindanhsachplaylist);
            tenalbum = itemView.findViewById(R.id.tenplaylistindanhsachplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",danhsachplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
