package model;

public class Yayinevleri {
    private int yayineviID;
    private String yayineviAdi;
    private String adres;
    private String telefonNumarasi;
    private String eposta;

    public Yayinevleri(int yayineviID, String yayineviAdi, String adres, String telefonNumarasi, String eposta) {
        this.yayineviID = yayineviID;
        this.yayineviAdi = yayineviAdi;
        this.adres = adres;
        this.telefonNumarasi = telefonNumarasi;
        this.eposta = eposta;
    }

    // Getters and setters
    public int getYayineviID() {
        return yayineviID;
    }

    public void setYayineviID(int yayineviID) {
        this.yayineviID = yayineviID;
    }

    public String getYayineviAdi() {
        return yayineviAdi;
    }

    public void setYayineviAdi(String yayineviAdi) {
        this.yayineviAdi = yayineviAdi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(String telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }
}
