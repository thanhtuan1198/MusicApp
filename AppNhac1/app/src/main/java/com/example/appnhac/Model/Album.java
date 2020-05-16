package com.example.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

@SerializedName("idalbum")
@Expose
private String idalbum;
@SerializedName("tenalbum")
@Expose
private String tenalbum;
@SerializedName("tencasi")
@Expose
private String tencasi;
@SerializedName("hinhalbum")
@Expose
private String hinhalbum;

public String getIdalbum() {
return idalbum;
}

public void setIdalbum(String idalbum) {
this.idalbum = idalbum;
}

public String getTenalbum() {
return tenalbum;
}

public void setTenalbum(String tenalbum) {
this.tenalbum = tenalbum;
}

public String getTencasi() {
return tencasi;
}

public void setTencasi(String tencasi) {
this.tencasi = tencasi;
}

public String getHinhalbum() {
return hinhalbum;
}

public void setHinhalbum(String hinhalbum) {
this.hinhalbum = hinhalbum;
}

}