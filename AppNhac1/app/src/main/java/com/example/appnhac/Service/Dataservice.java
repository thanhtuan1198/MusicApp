package com.example.appnhac.Service;

import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.ChudeTheloai;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.Model.RankBaihat;
import com.example.appnhac.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetDataPlaylist();

    @GET("chudeTheloai.php")
    Call<ChudeTheloai> GetChuDeTheLoai();

    @GET("album.php")
    Call<List<Album>> GetAlbum();

    @GET("baihatyeuthich.php")
    Call<List<RankBaihat>> GetRankBaihat();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<RankBaihat>> GetDanhSachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<RankBaihat>> Getdanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhSachPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<RankBaihat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("danhsachchude.php")
    Call<List<ChuDe>> GetDanhsachChude();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetDanhsachtheloaitheochude(@Field("idchude") String idchude);

    @GET("danhsachalbum.php")
    Call<List<Album>> GetDanhsachAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<RankBaihat>> GetDanhsachbaihatTheoAlbum(@Field("idalbum") String idalbum);

    @GET("top100baihat.php")
    Call<List<RankBaihat>> GetTop100baihat();

    @FormUrlEncoded
    @POST("like.php")
    Call<String> capnhatluotthich(@Field("idSong") String idbaihat);

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<RankBaihat>> GetSearchmusic(@Field("tukhoa")String tukhoa);
}
