package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnhac.Adapter.DanhsachChudeAdapter;
import com.example.appnhac.Adapter.DanhsachplaylistAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Danhsach_Chude extends Fragment {
    View view;
    RecyclerView recyclerViewdanhsachchude;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_danhsach_chude,container,false);
        recyclerViewdanhsachchude = view.findViewById(R.id.recyclerviewdanhsachchude);
        Getdata();
        return view;
    }

    private void Getdata() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.GetDanhsachChude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> danhsachchude = (ArrayList<ChuDe>) response.body();
                DanhsachChudeAdapter danhsachchudeAdapter = new DanhsachChudeAdapter(getActivity(),danhsachchude);
                recyclerViewdanhsachchude.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerViewdanhsachchude.setAdapter(danhsachchudeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {
                Getdata();
            }
        });
    }
}
