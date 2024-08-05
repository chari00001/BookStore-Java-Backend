package model;

import java.util.Date;

public class Kitaplar {
    private int kitapID;
    private String baslik;
    private int yazarID;
    private int kategoriID;
    private int yayineviID;
    private String ISBN;
    private double fiyat;
    private Date yayinTarihi;
    private String aciklama;
    private int sayfa;
    private int stok;

    // Constructor
    public Kitaplar(int kitapID,
                    String baslik,
                    int yazarID,
                    int kategoriID,
                    int yayineviID, 
                    String ISBN, 
                    double fiyat, 
                    Date yayinTarihi, 
                    String aciklama, 
                    int sayfa, int 
                    stok) {
        this.kitapID = kitapID;
        this.baslik = baslik;
        this.yazarID = yazarID;
        this.kategoriID = kategoriID;
        this.yayineviID = yayineviID;
        this.ISBN = ISBN;
        this.fiyat = fiyat;
        this.yayinTarihi = yayinTarihi;
        this.aciklama = aciklama;
        this.sayfa = sayfa;
        this.stok = stok;
    }

    // Getters and setters (optional)
    public int getKitapID() {
        return kitapID;
    }

    public void setKitapID(int kitapID) {
        this.kitapID = kitapID;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public int getYazarID() {
        return yazarID;
    }

    public void setYazarID(int yazarID) {
        this.yazarID = yazarID;
    }

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public int getYayineviID() {
        return yayineviID;
    }

    public void setYayineviID(int yayineviID) {
        this.yayineviID = yayineviID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public Date getYayinTarihi() {
        return yayinTarihi;
    }

    public void setYayinTarihi(Date yayinTarihi) {
        this.yayinTarihi = yayinTarihi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getSayfa() {
        return sayfa;
    }

    public void setSayfa(int sayfa) {
        this.sayfa = sayfa;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
