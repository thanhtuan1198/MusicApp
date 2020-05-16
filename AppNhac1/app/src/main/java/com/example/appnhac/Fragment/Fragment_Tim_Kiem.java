package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    RecyclerView recyclerViewsearchmusic;
    Toolbar toolbarsearchmusic;
    TextView textViewsearchmusic;
    ArrayList<RankBaihat> arrayListmusic;
    RankBaihatAdapter rankBaihatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        recyclerViewsearchmusic = view.findViewById(R.id.recyclerviewseachmusic);
        toolbarsearchmusic = view.findViewById(R.id.toolbarsearchmusic);
        textViewsearchmusic = view.findViewById(R.id.textviewsearchmusic);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarsearchmusic);
        toolbarsearchmusic.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                GetData(s);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewsearchmusic.setVisibility(View.VISIBLE);
                recyclerViewsearchmusic.setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                textViewsearchmusic.setVisibility(View.VISIBLE);
                recyclerViewsearchmusic.setVisibility(View.GONE);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void GetData(final String s) {
        Dataservice dataservice = APIService.getService();
        Call<List<RankBaihat>> callback = dataservice.GetSearchmusic(s);
        callback.enqueue(new Callback<List<RankBaihat>>() {
            @Override
            public void onResponse(Call<List<RankBaihat>> call, Response<List<RankBaihat>> response) {
                arrayListmusic = (ArrayList<RankBaihat>) response.body();
                if(arrayListmusic.size() > 0){
                    rankBaihatAdapter = new RankBaihatAdapter(getActivity(),arrayListmusic);
                    recyclerViewsearchmusic.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerViewsearchmusic.setAdapter(rankBaihatAdapter);
                    textViewsearchmusic.setVisibility(View.GONE);
                    recyclerViewsearchmusic.setVisibility(View.VISIBLE);
                }else {
                    textViewsearchmusic.setVisibility(View.VISIBLE);
                    recyclerViewsearchmusic.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<RankBaihat>> call, Throwable t) {
                GetData(s);
            }
        });
    }
}
