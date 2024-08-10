package model;

import java.sql.Timestamp;

public class Yorumlar {
  private int YorumID;
  private int MusteriID;
  private int KitapID;
  private int ParentID;
  private Timestamp Tarih;
  private String Yorum;

  public Yorumlar(int YorumID, int MusteriID, int KitapID, int ParentID, Timestamp Tarih, String Yorum) {
    this.YorumID = YorumID;
    this.MusteriID = MusteriID;
    this.KitapID = KitapID;
    this.ParentID = ParentID;
    this.Tarih = Tarih;
    this.Yorum = Yorum;
  }

  public int getYorumID() {
    return YorumID;
  }

  public void setYorumID(int YorumID) {
    this.YorumID = YorumID;
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

  public int getParentID() {
    return ParentID;
  }

  public void setParentID(int ParentID) {
    this.ParentID = ParentID;
  }

  public Timestamp getTarih() {
    return Tarih;
  }

  public void setTarih(Timestamp Tarih) {
    this.Tarih = Tarih;
  }

  public String getYorum() {
    return Yorum;
  }

  public void setYorum(String Yorum) {
    this.Yorum = Yorum;
  }
}
