package com.example.appnhac.Activity;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appnhac.Adapter.MainViewPagerAdapter;
import com.example.appnhac.Fragment.Fragment_Danhsach_Album;
import com.example.appnhac.Fragment.Fragment_Danhsach_Chude;
import com.example.appnhac.Fragment.Fragment_Tim_Kiem;
import com.example.appnhac.Fragment.Fragment_Top100baihat;
import com.example.appnhac.Fragment.Fragment_Trang_Chu;
import com.example.appnhac.Fragment.Fragment_danhsach_playlist;
import com.example.appnhac.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm kiếm");

        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void anhxa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
