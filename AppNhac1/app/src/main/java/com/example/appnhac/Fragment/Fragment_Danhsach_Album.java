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

import com.example.appnhac.Adapter.AlbumAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Danhsach_Album extends Fragment {
    View view;
    RecyclerView recyclerViewdanhsachAlbum;
    AlbumAdapter albumAdapter;
    ArrayList<Album> danhsachAlbum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danhsach_album,container,false);
        recyclerViewdanhsachAlbum = view.findViewById(R.id.recyclerviewdanhsachalbum);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetDanhsachAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                danhsachAlbum = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),danhsachAlbum);
                recyclerViewdanhsachAlbum.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerViewdanhsachAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                GetData();
            }
        });
    }
}
