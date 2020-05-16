package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appnhac.Adapter.RankBaihatAdapter;
import com.example.appnhac.Model.RankBaihat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Top100baihat extends Fragment {
    View view;
    RecyclerView recyclerViewtop100baihat;
    RankBaihatAdapter top100baihatAdapter;
    ArrayList<RankBaihat> top100baihat;
    TextView txxtplaylist;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank_baihat,container,false);
        recyclerViewtop100baihat = view.findViewById(R.id.recyclerviewRankbaihat);
        txxtplaylist = view.findViewById(R.id.txtrankbaihat);
        txxtplaylist.setText("Top 100 bài hát");

        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<RankBaihat>> callback = dataservice.GetTop100baihat();
        callback.enqueue(new Callback<List<RankBaihat>>() {
            @Override
            public void onResponse(Call<List<RankBaihat>> call, Response<List<RankBaihat>> response) {
                top100baihat = (ArrayList<RankBaihat>) response.body();
                top100baihatAdapter = new RankBaihatAdapter(getActivity(),top100baihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewtop100baihat.setLayoutManager(linearLayoutManager);
                recyclerViewtop100baihat.setAdapter(top100baihatAdapter);
            }

            @Override
            public void onFailure(Call<List<RankBaihat>> call, Throwable t) {
                GetData();
            }
        });
    }
}
