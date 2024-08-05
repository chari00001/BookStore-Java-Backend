package controllers;

import model.Kitaplar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import src.DatabaseConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KitaplarController {
    private Connection connection;

    public KitaplarController(Connection connection) {
        DatabaseConnection db = new DatabaseConnection();
        db.connect(db.getJdbcUrl(), db.getUsername(), db.getPassword());

        this.connection = connection;
    }

    public List<Kitaplar> selectKitaplar() {
        List<Kitaplar> kitaplarList = new ArrayList<>();
        String query = "SELECT * FROM Kitaplar";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int kitapID = rs.getInt("KitapID");
                String baslik = rs.getString("Baslik");
                int yazarID = rs.getInt("YazarID");
                int kategoriID = rs.getInt("KategoriID");
                int yayineviID = rs.getInt("YayineviID");
                String ISBN = rs.getString("ISBN");
                double fiyat = rs.getDouble("Fiyat");
                Date yayinTarihi = rs.getDate("YayinTarihi");
                String aciklama = rs.getString("Aciklama");
                int sayfa = rs.getInt("Sayfa");
                int stok = rs.getInt("Stok");

                Kitaplar kitap = new Kitaplar(kitapID, baslik, yazarID, kategoriID, yayineviID, ISBN, fiyat,
                        yayinTarihi, aciklama, sayfa, stok);
                kitaplarList.add(kitap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kitaplarList;
    }

    public Kitaplar selectKitap(int id) {
        Kitaplar kitap = null;
        String query = "SELECT * FROM Kitaplar WHERE KitapID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Set the parameter for the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Check if the ResultSet contains any data
                    kitap = new Kitaplar(
                            rs.getInt("KitapID"),
                            rs.getString("Baslik"),
                            rs.getInt("YazarID"),
                            rs.getInt("KategoriID"),
                            rs.getInt("YayineviID"),
                            rs.getString("ISBN"),
                            rs.getDouble("Fiyat"),
                            rs.getDate("YayinTarihi"),
                            rs.getString("Aciklama"),
                            rs.getInt("Sayfa"),
                            rs.getInt("Stok"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kitap;
    }

    public void insertKitap(Kitaplar kitap) {
        String query = "INSERT INTO Kitaplar (KitapID, Baslik, YazarID, KategoriID, YayineviID, ISBN, Fiyat, YayinTarihi, Aciklama, Sayfa, Stok) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, kitap.getKitapID());
            stmt.setString(2, kitap.getBaslik());
            stmt.setInt(3, kitap.getYazarID());
            stmt.setInt(4, kitap.getKategoriID());
            stmt.setInt(5, kitap.getYayineviID());
            stmt.setString(6, kitap.getISBN());
            stmt.setDouble(7, kitap.getFiyat());
            stmt.setDate(8, new Date(kitap.getYayinTarihi().getTime()));
            stmt.setString(9, kitap.getAciklama());
            stmt.setInt(10, kitap.getSayfa());
            stmt.setInt(11, kitap.getStok());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateKitap(Kitaplar kitap) {
        String query = "UPDATE Kitaplar SET Baslik = ?, YazarID = ?, KategoriID = ?, YayineviID = ?, ISBN = ?, Fiyat = ?, YayinTarihi = ?, Aciklama = ?, Sayfa = ?, Stok = ? WHERE KitapID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kitap.getBaslik());
            stmt.setInt(2, kitap.getYazarID());
            stmt.setInt(3, kitap.getKategoriID());
            stmt.setInt(4, kitap.getYayineviID());
            stmt.setString(5, kitap.getISBN());
            stmt.setDouble(6, kitap.getFiyat());
            stmt.setDate(7, new Date(kitap.getYayinTarihi().getTime())); // Convert java.util.Date to java.sql.Date
            stmt.setString(8, kitap.getAciklama());
            stmt.setInt(9, kitap.getSayfa());
            stmt.setInt(10, kitap.getStok());
            stmt.setInt(11, kitap.getKitapID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKitap(int id) {
        String query = "DELETE FROM Kitaplar WHERE KitapID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void decreaseStock(int id) {
        String query = "UPDATE Kitaplar SET Stok = Stok - 1 WHERE KitapID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
