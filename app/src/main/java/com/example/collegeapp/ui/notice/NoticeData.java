package com.example.collegeapp.ui.notice;

public class NoticeData {
    String titile,img,date,time,key;
    NoticeData(){

    }

    public NoticeData(String titile, String img, String date, String time, String key) {
        this.titile = titile;
        this.img = img;
        this.date = date;
        this.time = time;
        this.key = key;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
