package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Activity.PlaymusicActivity;
import com.example.appnhac.Model.RankBaihat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankBaihatAdapter extends RecyclerView.Adapter<RankBaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<RankBaihat> arrayListRankbaihat;

    public RankBaihatAdapter(Context context, ArrayList<RankBaihat> arrayListRankbaihat) {
        this.context = context;
        this.arrayListRankbaihat = arrayListRankbaihat;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_rank_baihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RankBaihat rankBaihat = arrayListRankbaihat.get(position);
        holder.txttenbaihat.setText(rankBaihat.getTenbaihat());
        holder.txttencasi.setText(rankBaihat.getCasi());
        Picasso.with(context).load(rankBaihat.getHinhbaihat()).into(holder.hinhbaihat);
    }

    @Override
    public int getItemCount() {
        return arrayListRankbaihat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenbaihat,txttencasi;
        ImageView hinhbaihat,iconluotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.tenbaihathot);
            txttencasi = itemView.findViewById(R.id.casihaihathot);
            hinhbaihat = itemView.findViewById(R.id.hinhbaihathot);
            iconluotthich = itemView.findViewById(R.id.iconluotthich);
            iconluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.capnhatluotthich(arrayListRankbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("success")){
                                Toast.makeText(context, "Da thich", Toast.LENGTH_SHORT).show();
                                iconluotthich.setEnabled(false);
                                iconluotthich.setImageResource(R.drawable.iconloved);
                            }
                            else Toast.makeText(context, "Loi", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlaymusicActivity.class);
                    intent.putExtra("baihat",arrayListRankbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
