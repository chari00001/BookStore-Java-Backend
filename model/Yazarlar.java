package model;

public class Yazarlar {
    private int yazarID;
    private String ad;
    private String soyad;
    private String biyografi;

    public Yazarlar(int yazarID, String ad, String soyad, String biyografi) {
        this.yazarID = yazarID;
        this.ad = ad;
        this.soyad = soyad;
        this.biyografi = biyografi;
    }

    // Getters and setters
    public int getYazarID() {
        return yazarID;
    }

    public void setYazarID(int yazarID) {
        this.yazarID = yazarID;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getBiyografi() {
        return biyografi;
    }

    public void setBiyografi(String biyografi) {
        this.biyografi = biyografi;
    }
}
