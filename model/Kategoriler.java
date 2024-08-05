package model;

public class Kategoriler {
  private int kategoriID;
  private String kategoriAdi;
  private String aciklama;

  public Kategoriler(int kategoriID, String kategoriAdi, String aciklama) {
    this.kategoriID = kategoriID;
    this.kategoriAdi = kategoriAdi;
    this.aciklama = aciklama;
  }

  // Getters and setters
  public int getKategoriID() {
    return kategoriID;
  }

  public void setKategoriID(int kategoriID) {
    this.kategoriID = kategoriID;
  }

  public String getKategoriAdi() {
    return kategoriAdi;
  }

  public void setKategoriAdi(String kategoriAdi) {
    this.kategoriAdi = kategoriAdi;
  }

  public String getAciklama() {
    return aciklama;
  }

  public void setAciklama(String aciklama) {
    this.aciklama = aciklama;
  }
}
