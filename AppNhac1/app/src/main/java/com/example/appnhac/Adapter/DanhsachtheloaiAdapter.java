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
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtheloaiAdapter extends RecyclerView.Adapter<DanhsachtheloaiAdapter.ViewHolder> {

    Context context;
    ArrayList<TheLoai> danhsachtheloaitheochude;

    public DanhsachtheloaiAdapter(Context context, ArrayList<TheLoai> danhsachtheloaitheochude) {
        this.context = context;
        this.danhsachtheloaitheochude = danhsachtheloaitheochude;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_danhsach_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TheLoai theLoai = danhsachtheloaitheochude.get(position);
        Picasso.with(context).load(theLoai.getHinhtheloai()).into(holder.imgtheloai);
        holder.tentheloai.setText(theLoai.getTentheloai());
    }

    @Override
    public int getItemCount() {
        return danhsachtheloaitheochude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgtheloai;
        TextView tentheloai;
        public ViewHolder(View itemView) {
            super(itemView);
            imgtheloai = itemView.findViewById(R.id.imgindanhsachplaylist);
            tentheloai = itemView.findViewById(R.id.tenplaylistindanhsachplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idtheloai",danhsachtheloaitheochude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
