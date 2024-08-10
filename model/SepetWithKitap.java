package model;

public class SepetWithKitap {
  private int SepetID;
  private int MusteriID;
  private int KitapID;
  private int Adet;
  private String Baslik;
  private int YazarID;
  private double Fiyat;

  // Constructor
  public SepetWithKitap(int SepetID, int MusteriID, int KitapID, int Adet, String Baslik, int YazarID,
      double Fiyat) {
    this.SepetID = SepetID;
    this.MusteriID = MusteriID;
    this.KitapID = KitapID;
    this.Adet = Adet;
    this.Baslik = Baslik;
    this.YazarID = YazarID;
    this.Fiyat = Fiyat;
  }

  // Getters and setters
  public int getSepetID() {
    return SepetID;
  }

  public void setSepetID(int SepetID) {
    this.SepetID = SepetID;
  }

  public int getMusteriID() {
    return MusteriID;
  }

  public void setMusteriID(int MusteriID) {
    this.MusteriID = MusteriID;
  }

  public int getKitapID() {
    return KitapID;
  }

  public void setKitapID(int KitapID) {
    this.KitapID = KitapID;
  }

  public int getAdet() {
    return Adet;
  }

  public void setAdet(int Adet) {
    this.Adet = Adet;
  }

  public String getBaslik() {
    return Baslik;
  }

  public void setBaslik(String Baslik) {
    this.Baslik = Baslik;
  }

  public int getYazarID() {
    return YazarID;
  }

  public void setYazarID(int YazarID) {
    this.YazarID = YazarID;
  }

  public double getFiyat() {
    return Fiyat;
  }

  public void setFiyat(double Fiyat) {
    this.Fiyat = Fiyat;
  }
}
