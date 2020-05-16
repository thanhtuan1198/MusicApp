package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends  RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> arrayListAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrayListAlbum) {
        this.context = context;
        this.arrayListAlbum = arrayListAlbum;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = arrayListAlbum.get(position);
        holder.txttencasi.setText(album.getTencasi());
        holder.txttenalbum.setText(album.getTenalbum());
        Picasso.with(context).load(album.getHinhalbum()).into(holder.imgalbum);
    }

    @Override
    public int getItemCount() {
        return arrayListAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgalbum;
        TextView txttenalbum,txttencasi;
        public ViewHolder(View itemView) {
            super(itemView);
            imgalbum = itemView.findViewById(R.id.imgAlbum);
            txttenalbum = itemView.findViewById(R.id.txttenalbum);
            txttencasi = itemView.findViewById(R.id.txttencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idalbum",arrayListAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
