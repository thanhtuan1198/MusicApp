package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class Fragment_Rank_Baihat extends Fragment {

    View view;
    RecyclerView recyclerViewrankbaihat;
    RankBaihatAdapter rankBaihatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank_baihat,container,false);
        recyclerViewrankbaihat = view.findViewById(R.id.recyclerviewRankbaihat);
        getData();
        return view;
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<RankBaihat>> callback = dataservice.GetRankBaihat();
        callback.enqueue(new Callback<List<RankBaihat>>() {
            @Override
            public void onResponse(Call<List<RankBaihat>> call, Response<List<RankBaihat>> response) {
                ArrayList<RankBaihat> arrayListRankbaihat = (ArrayList<RankBaihat>) response.body();
                rankBaihatAdapter = new RankBaihatAdapter(getActivity(),arrayListRankbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewrankbaihat.setLayoutManager(linearLayoutManager);
                recyclerViewrankbaihat.setAdapter(rankBaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<RankBaihat>> call, Throwable t) {
                getData();
            }
        });
    }
}
