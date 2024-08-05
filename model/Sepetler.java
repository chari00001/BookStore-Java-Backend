package model;

public class Sepetler {
  private int SepetID;
  private int MusteriID;
  private int KitapID;
  private int Adet;

  public Sepetler(int SepetID, int MusteriID, int KitapID, int Adet) {
    this.SepetID = SepetID;
    this.MusteriID = MusteriID;
    this.KitapID = KitapID;
    this.Adet = Adet;
  }

  // Getters and setters
  public int getSepetID() {
    return SepetID;
  }

  public void setSepetID(int SepetID) {
    this.SepetID = SepetID;
  }

  public void setMusteriID(int MusteriID) {
    this.MusteriID = MusteriID;
  }

  public int getMusteriID() {
    return MusteriID;
  }

  public void setKitapID(int KitapID) {
    this.KitapID = KitapID;
  }

  public int getKitapID() {
    return KitapID;
  }

  public void setAdet(int Adet) {
    this.Adet = Adet;
  }

  public int getAdet() {
    return Adet;
  }
}
