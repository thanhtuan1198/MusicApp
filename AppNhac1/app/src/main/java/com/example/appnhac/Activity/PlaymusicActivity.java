package com.example.appnhac.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Adapter.ViewPagerPlayMusic;
import com.example.appnhac.Fragment.Fragment_Dianhac;
import com.example.appnhac.Fragment.Fragment_ListMusic_Playmusic;
import com.example.appnhac.Model.RankBaihat;
import com.example.appnhac.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlaymusicActivity extends AppCompatActivity {
    SeekBar seekBarplaymusic;
    Toolbar toolbarplaymusic;
    TextView textviewtimesong,textviewtotaltimesong;
    ImageButton repeat,suffle,pre,next,play;
    ViewPager viewPagerplaymusic;
    public static ArrayList<RankBaihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlayMusic viewPagerPlayMusicAdapter;
    Fragment_Dianhac fragment_dianhac;
    Fragment_ListMusic_Playmusic fragment_listMusic_playmusic;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeatmusic = false, checkrandom = false, nextmusic = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmusic);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetdataIntent();
        Anhxa();

        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(viewPagerPlayMusicAdapter.getItem(1) != null){
                    if(mangbaihat.size() > 0 ){
                        fragment_dianhac.Playnhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.iconplay);
                }else {
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.iconpause);
                }
            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repeatmusic == false){
                    if(checkrandom == true){
                        checkrandom = false;
                        suffle.setImageResource(R.drawable.iconsuffle);
                    }
                    repeat.setImageResource(R.drawable.iconsyned);
                    repeatmusic = true;
                }else {
                    repeat.setImageResource(R.drawable.iconrepeat);
                    repeatmusic = false;
                }
            }
        });
        suffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkrandom == false){
                    if(repeatmusic == true){
                        repeatmusic = false;
                        repeat.setImageResource(R.drawable.iconrepeat);
                    }
                    suffle.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else {
                    suffle.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBarplaymusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size() > 0 ){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < mangbaihat.size()){
                        play.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeatmusic == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position-=1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > mangbaihat.size() -1){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                next.setClickable(false);
                pre.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next.setClickable(true);
                        pre.setClickable(true);
                    }
                },5000);
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size() > 0 ){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < mangbaihat.size()){
                        play.setImageResource(R.drawable.iconpause);
                        position--;
                        if(position < 0){
                            position = mangbaihat.size() - 1;
                        }
                        if(repeatmusic == true){

                            position+=1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }

                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                next.setClickable(false);
                pre.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next.setClickable(true);
                        pre.setClickable(true);
                    }
                },5000);
            }
        });
    }



    private void GetdataIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if(intent != null) {
            if (intent.hasExtra("baihat")) {
                RankBaihat baihat = intent.getParcelableExtra("baihat");
                mangbaihat.add(baihat);
            } else if (intent.hasExtra("tatcabaihat")) {
                ArrayList<RankBaihat> arrayListbaihat = intent.getParcelableArrayListExtra("tatcabaihat");
                mangbaihat = arrayListbaihat;
            }
        }
    }

    private void Anhxa() {
        seekBarplaymusic = findViewById(R.id.seekbarplaymusic);
        toolbarplaymusic = findViewById(R.id.toolbarplaymusic);
        textviewtimesong = findViewById(R.id.textviewtimesong);
        textviewtotaltimesong = findViewById(R.id.textviewtotaltimesong);
        repeat = findViewById(R.id.imgbuttonrepeat);
        play = findViewById(R.id.imgbuttonplay);
        suffle = findViewById(R.id.imgbuttonsuffle);
        pre = findViewById(R.id.imgbuttonpre);
        next = findViewById(R.id.imgbuttonnext);
        viewPagerplaymusic = findViewById(R.id.viewpagerplaymusic);
        setSupportActionBar(toolbarplaymusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaymusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
//                mediaPlayer.reset();
                mangbaihat.clear();
            }
        });
        toolbarplaymusic.setTitleTextColor(Color.WHITE);
        fragment_dianhac = new Fragment_Dianhac();
        fragment_listMusic_playmusic = new Fragment_ListMusic_Playmusic();
        viewPagerPlayMusicAdapter = new ViewPagerPlayMusic(getSupportFragmentManager());
        viewPagerPlayMusicAdapter.addfragment(fragment_listMusic_playmusic);
        viewPagerPlayMusicAdapter.addfragment(fragment_dianhac);
        viewPagerplaymusic.setAdapter(viewPagerPlayMusicAdapter);
        fragment_dianhac = (Fragment_Dianhac) viewPagerPlayMusicAdapter.getItem(1);
        if(mangbaihat.size() > 0 ){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            play.setImageResource(R.drawable.iconpause);
        }
    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textviewtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarplaymusic.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    seekBarplaymusic.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textviewtimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            nextmusic = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(nextmusic == true) {
                    if (position < mangbaihat.size()) {
                        play.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeatmusic == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > mangbaihat.size() - 1) {
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                    }

                next.setClickable(false);
                pre.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next.setClickable(true);
                        pre.setClickable(true);
                    }
                },5000);
                nextmusic = false;
                handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
