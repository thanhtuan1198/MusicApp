package com.example.appnhac.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.appnhac.Adapter.DanhsachtheloaiAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheloaitheochudeActivity extends AppCompatActivity {

    RecyclerView recyclerViewdanhsachtheloaitheochude;
    Toolbar toolbardanhsachtheloaitheochude;
    ChuDe chuDe;
    ArrayList<TheLoai> arrayListtheloaitheochude;
    DanhsachtheloaiAdapter danhsachtheloaiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloaitheochude);
        init();
        DataIntent();
        GetDatatheloai(chuDe.getIdchude());
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("idchude")){
            chuDe = (ChuDe) intent.getSerializableExtra("idchude");
            toolbardanhsachtheloaitheochude.setTitle(chuDe.getTenchude());
        }
    }

    private void GetDatatheloai(String idchude) {
        Dataservice dataservice = APIService.getService();
        Call<List<TheLoai>> callback = dataservice.GetDanhsachtheloaitheochude(idchude);
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                arrayListtheloaitheochude = (ArrayList<TheLoai>) response.body();
                danhsachtheloaiAdapter = new DanhsachtheloaiAdapter(TheloaitheochudeActivity.this,arrayListtheloaitheochude);
                recyclerViewdanhsachtheloaitheochude.setLayoutManager(new GridLayoutManager(TheloaitheochudeActivity.this,2));
                recyclerViewdanhsachtheloaitheochude.setAdapter(danhsachtheloaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewdanhsachtheloaitheochude= findViewById(R.id.recyclerviewdanhsachtheloaitheochude);
        toolbardanhsachtheloaitheochude = findViewById(R.id.toolbardanhsachtheloai);
        setSupportActionBar(toolbardanhsachtheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardanhsachtheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
