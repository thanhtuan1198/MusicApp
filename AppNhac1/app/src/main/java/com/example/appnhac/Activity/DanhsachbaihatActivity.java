package com.example.appnhac.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appnhac.Adapter.DanhsachbaihatAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.Model.RankBaihat;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DanhsachbaihatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imageViewbaihat;
    Quangcao quangcao;
    ArrayList<RankBaihat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        Anhxa();

        Init();
        if(quangcao !=null && !quangcao.getTenBaiHat().equals("")){
            setValueinView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getIdQuangCao());
        }
        if(playlist != null && !playlist.getTen().equals("")){
            setValueinView(playlist.getTen(),playlist.getHinh());
            GetDataPlaylist(playlist.getIdplaylist());
//            Toast.makeText(this, playlist.getIdplaylist(), Toast.LENGTH_SHORT).show();
        }
        if(theLoai !=null && !theLoai.getTentheloai().equals("")){
            setValueinView(theLoai.getTentheloai(),theLoai.getHinhtheloai());
            GetDataTheloai(theLoai.getIdtheloai());
        }
        if(album != null && !album.getTenalbum().equals("")){
            setValueinView(album.getTenalbum(),album.getHinhalbum());
            GetDataAlbum(album.getIdalbum());
        }
    }

    private void GetDataAlbum(final String idalbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<RankBaihat>> callback = dataservice.GetDanhsachbaihatTheoAlbum(idalbum);
        callback.enqueue(new Callback<List<RankBaihat>>() {
            @Override
            public void onResponse(Call<List<RankBaihat>> call, Response<List<RankBaihat>> response) {
                mangbaihat = (ArrayList<RankBaihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventclickmusics();
            }

            @Override
            public void onFailure(Call<List<RankBaihat>> call, Throwable t) {
                GetDataAlbum(idalbum);
            }
        });
    }

    private void GetDataTheloai(final String idtheloai) {
        Dataservice dataservice = APIService.getService();
        Call<List<RankBaihat>> callback = dataservice.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<RankBaihat>>() {
            @Override
            public void onResponse(Call<List<RankBaihat>> call, Response<List<RankBaihat>> response) {
                mangbaihat = (ArrayList<RankBaihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventclickmusics();
            }

            @Override
            public void onFailure(Call<List<RankBaihat>> call, Throwable t) {
                GetDataTheloai(idtheloai);
            }
        });

    }

    private void GetDataPlaylist(final String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<RankBaihat>> callback = dataservice.Getdanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<RankBaihat>>() {
            @Override
            public void onResponse(Call<List<RankBaihat>> call, Response<List<RankBaihat>> response) {
                mangbaihat = (ArrayList<RankBaihat>) response.body();
//                Log.d("BBB",mangbaihat.get(0).getTenbaihat());
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventclickmusics();
            }

            @Override
            public void onFailure(Call<List<RankBaihat>> call, Throwable t) {
                GetDataPlaylist(idplaylist);
            }
        });
    }

    private void GetDataQuangcao(final String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<RankBaihat>> callback = dataservice.GetDanhSachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<RankBaihat>>() {
            @Override
            public void onResponse(Call<List<RankBaihat>> call, Response<List<RankBaihat>> response) {
                mangbaihat = (ArrayList<RankBaihat>) response.body();

                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventclickmusics();
            }

            @Override
            public void onFailure(Call<List<RankBaihat>> call, Throwable t) {
                GetDataQuangcao(idquangcao);
            }
        });
    }

    private void setValueinView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imageViewbaihat);
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);

    }

    private void Anhxa() {
        coordinatorLayout = findViewById(R.id.coordinator);
        appBarLayout = findViewById(R.id.appbarlayout);
        collapsingToolbarLayout = findViewById(R.id.colapsetoolbar);
        toolbar = findViewById(R.id.tollbardanhsach);
        imageViewbaihat = findViewById(R.id.imgviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingbutton);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");

            }
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");

            }
            if(intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("idalbum")){
                album = (Album) intent.getSerializableExtra("idalbum");
            }
        }
    }

    private void eventclickmusics(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this,PlaymusicActivity.class);
                intent.putExtra("tatcabaihat", mangbaihat);
                startActivity(intent);
            }
        });
    }
}
