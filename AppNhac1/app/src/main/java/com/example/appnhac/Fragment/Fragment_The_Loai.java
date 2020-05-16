package com.example.appnhac.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appnhac.Activity.ChuDeActivity;
import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Activity.MainActivity;
import com.example.appnhac.Activity.TheloaitheochudeActivity;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.ChudeTheloai;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_The_Loai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView textView,xemthem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_the_loai,container,false);
        horizontalScrollView = view.findViewById(R.id.horscrollview);
        textView = view.findViewById(R.id.tenchudetheloai);
        xemthem = view.findViewById(R.id.txtxemthem);
        xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChuDeActivity.class);
                getActivity().startActivity(intent);
            }
        });
        getData();

        return view;
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<ChudeTheloai> callback = dataservice.GetChuDeTheLoai();
        callback.enqueue(new Callback<ChudeTheloai>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ChudeTheloai> call, Response<ChudeTheloai> response) {
                ChudeTheloai chudeTheloai = (ChudeTheloai) response.body();
                final ArrayList<ChuDe> arrayListchude = new ArrayList<>();
                arrayListchude.addAll(chudeTheloai.getChuDe());
                final ArrayList<TheLoai> arrayListtheloai = new ArrayList<>();
                arrayListtheloai.addAll(chudeTheloai.getTheLoai());
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);

                for (int i=0 ; i < arrayListchude.size();i++){
                    CardView cardView =new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(arrayListchude.get(i).getIdchude() != null){
                        Picasso.with(getActivity()).load(arrayListchude.get(i).getHinhchude()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), TheloaitheochudeActivity.class);
                            intent.putExtra("idchude",arrayListchude.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for (int j=0 ; j < arrayListtheloai.size();j++){
                    CardView cardView =new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(arrayListtheloai.get(j).getIdchude() != null){
                        Picasso.with(getActivity()).load(arrayListtheloai.get(j).getHinhtheloai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",arrayListtheloai.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChudeTheloai> call, Throwable t) {
                getData();
            }
        });
    }
}
