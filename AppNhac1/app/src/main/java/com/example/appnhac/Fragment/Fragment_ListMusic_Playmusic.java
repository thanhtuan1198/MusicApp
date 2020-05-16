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

import com.example.appnhac.Activity.PlaymusicActivity;
import com.example.appnhac.Adapter.ListplaymusicAdapter;
import com.example.appnhac.R;

public class Fragment_ListMusic_Playmusic extends Fragment {
    View view;
    RecyclerView recyclerViewlistsongplaymusic;
    ListplaymusicAdapter listplaymusicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listmusic_playmusic,container,false);
        recyclerViewlistsongplaymusic = view.findViewById(R.id.recyclerviewplaymusiclistsong);
        if(PlaymusicActivity.mangbaihat.size() > 0) {
            listplaymusicAdapter = new ListplaymusicAdapter(getActivity(), PlaymusicActivity.mangbaihat);
            Log.d("BBB",PlaymusicActivity.mangbaihat.get(0).getTenbaihat());
            recyclerViewlistsongplaymusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewlistsongplaymusic.setAdapter(listplaymusicAdapter);
        }
        return view;
    }
}
