package model;

public class Musteriler {
    private int MusteriID;
    private String Ad;
    private String Soyad;
    private String Eposta;
    private String Sifre;
    private String Adres;
    private String TelefonNumarasi;
    private int isAdmin;

    public Musteriler(int MusteriID, String Ad, String Soyad, String Eposta, String Sifre, String Adres,
            String TelefonNumarasi,
            int isAdmin) {
        this.Ad = Ad;
        this.Soyad = Soyad;
        this.Eposta = Eposta;
        this.Sifre = Sifre;
        this.Adres = Adres;
        this.TelefonNumarasi = TelefonNumarasi;
        this.isAdmin = isAdmin;
    }

    // Getters and setters
    public int getMusteriID() {
        return MusteriID;
    }

    public void setMusteriID(int MusteriID) {
        this.MusteriID = MusteriID;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String Ad) {
        this.Ad = Ad;
    }

    public String getSoyad() {
        return Soyad;
    }

    public void setSoyad(String Soyad) {
        this.Soyad = Soyad;
    }

    public String getEposta() {
        return Eposta;
    }

    public void setEposta(String Eposta) {
        this.Eposta = Eposta;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String Sifre) {
        this.Sifre = Sifre;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String Adres) {
        this.Adres = Adres;
    }

    public String getTelefonNumarasi() {
        return TelefonNumarasi;
    }

    public void setTelefonNumarasi(String TelefonNumarasi) {
        this.TelefonNumarasi = TelefonNumarasi;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
