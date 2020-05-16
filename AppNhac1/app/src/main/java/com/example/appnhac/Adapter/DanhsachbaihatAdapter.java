package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Activity.PlaymusicActivity;
import com.example.appnhac.Model.RankBaihat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<RankBaihat> mangbaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<RankBaihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RankBaihat baihat = mangbaihat.get(position);
        holder.txtindex.setText(position + 1 + "");
        holder.tenbaihat.setText(baihat.getTenbaihat());
        holder.casi.setText(baihat.getCasi());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,tenbaihat,casi;
        ImageView imgluotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.txtdanhsachbaihatindex);
            tenbaihat = itemView.findViewById(R.id.textviewdanhsachbaihat);
            casi = itemView.findViewById(R.id.textviewcasibaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewdanhsachbaihat);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.capnhatluotthich(mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("success")){
                                Toast.makeText(context, "Da thich", Toast.LENGTH_SHORT).show();
                                imgluotthich.setEnabled(false);
                                imgluotthich.setImageResource(R.drawable.iconloved);
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
                    intent.putExtra("baihat",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
