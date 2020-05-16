package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Adapter.DanhsachplaylistAdapter;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_danhsach_playlist extends Fragment {
    View view;
    RecyclerView recyclerViewindanhsachplayist;
    DanhsachplaylistAdapter danhsachplaylistAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danhsach_playlist,container,false);
        recyclerViewindanhsachplayist = view.findViewById(R.id.recyclerviewdanhsachplaylist);

        Getdata();
        return view;
    }

    private void Getdata() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetDanhSachPlayList();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> danhsachhplaylist = (ArrayList<Playlist>) response.body();
                danhsachplaylistAdapter = new DanhsachplaylistAdapter(getActivity(),danhsachhplaylist);
                recyclerViewindanhsachplayist.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerViewindanhsachplayist.setAdapter(danhsachplaylistAdapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Getdata();
            }
        });
    }
}
