package controllers;

import model.SepetWithKitap;
import model.Sepetler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SepetlerController {
  private Connection connection;

  public SepetlerController(Connection connection) {
    this.connection = connection;
  }

  public List<Sepetler> selectSepetler() {
    List<Sepetler> sepetlerList = new ArrayList<>();
    String query = "SELECT * FROM Sepetler";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        int SepetID = rs.getInt("SepetID");
        int SepetAdi = rs.getInt("MusteriID");
        int adres = rs.getInt("KitapID");
        int telefonNumarasi = rs.getInt("Adet");

        Sepetler sepet = new Sepetler(SepetID, SepetAdi, adres, telefonNumarasi);
        sepetlerList.add(sepet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sepetlerList;
  }

  public Sepetler selectSepet(int id) {
    Sepetler sepet = null;
    String query = "SELECT * FROM Sepetler WHERE SepetID = ?";

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id); // Set the parameter for the query
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) { // Check if the ResultSet contains any data
          sepet = new Sepetler(
              rs.getInt("SepetID"),
              rs.getInt("MusteriID"),
              rs.getInt("KitapID"),
              rs.getInt("Adet"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sepet;
  }

  public List<SepetWithKitap> selectSepetlerByUserId(int id) {
    List<SepetWithKitap> sepetWithKitapList = new ArrayList<>();
    String query = "SELECT s.SepetID, s.MusteriID, s.KitapID, s.Adet, k.Baslik, k.YazarID, k.Fiyat " +
        "FROM Sepetler s " +
        "JOIN Kitaplar k ON s.KitapID = k.KitapID " +
        "WHERE s.MusteriID = ?";

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        int SepetID = rs.getInt("SepetID");
        int MusteriID = rs.getInt("MusteriID");
        int KitapID = rs.getInt("KitapID");
        int Adet = rs.getInt("Adet");
        String Baslik = rs.getString("Baslik");
        int YazarID = rs.getInt("YazarID");
        double Fiyat = rs.getDouble("Fiyat");

        SepetWithKitap sepetWithKitap = new SepetWithKitap(SepetID, MusteriID, KitapID, Adet, Baslik, YazarID, Fiyat);

        sepetWithKitapList.add(sepetWithKitap);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return sepetWithKitapList;
  }

  public void insertSepet(Sepetler sepet) {
    String query = "INSERT INTO Sepetler (MusteriID, KitapID, Adet) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, sepet.getMusteriID());
      stmt.setInt(2, sepet.getKitapID());
      stmt.setInt(3, sepet.getAdet());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateSepet(int itemId, int quantity) {
    String query = "UPDATE Sepetler SET Adet = ? WHERE SepetID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, quantity);
      stmt.setInt(2, itemId); // Bind the itemId to the query
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteSepet(int id) {
    String query = "DELETE FROM Sepetler WHERE SepetID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}