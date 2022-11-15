package com.foysaldev.leduchoang_2050531200150_bt10_ketnoifirebase;

import java.io.Serializable;

public class CongAn implements Serializable {
    String name, nameSingel, img, time,star;

    CongAn() {

    }

    public CongAn(String name, String nameSingel, String img, String time,String star) {
        this.name = name;
        this.nameSingel = nameSingel;
        this.img = img;
        this.time = time;
        this.star=star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSingel() {
        return nameSingel;
    }

    public void setNameSingel(String nameSingel) {
        this.nameSingel = nameSingel;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
