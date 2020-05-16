package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnhac.Activity.TheloaitheochudeActivity;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachChudeAdapter  extends RecyclerView.Adapter<DanhsachChudeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> danhsachchude;

    public DanhsachChudeAdapter(Context context, ArrayList<ChuDe> danhsachchude) {
        this.context = context;
        this.danhsachchude = danhsachchude;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_danhsach_chude,parent,false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChuDe chuDe = danhsachchude.get(position);
        Picasso.with(context).load(chuDe.getHinhchude()).into(holder.hinhindanhsachchude);
        holder.tenchude.setText(chuDe.getTenchude());
    }

    @Override
    public int getItemCount() {
        return danhsachchude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView hinhindanhsachchude;
        TextView tenchude;
        public ViewHolder(View itemView) {
            super(itemView);
            hinhindanhsachchude = itemView.findViewById(R.id.imgindanhsachchude);
            tenchude = itemView.findViewById(R.id.tenchudeindanhsachchude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TheloaitheochudeActivity.class);
                    intent.putExtra("idchude",danhsachchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
