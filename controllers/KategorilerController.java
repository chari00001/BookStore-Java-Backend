package controllers;

import model.Kategoriler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KategorilerController {
    private Connection connection;

    public KategorilerController(Connection connection) {
        this.connection = connection;
    }

    public List<Kategoriler> selectKategoriler() {
        List<Kategoriler> kategorilerList = new ArrayList<>();
        String query = "SELECT * FROM Kategoriler";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int kategoriID = rs.getInt("KategoriID");
                String kategoriAdi = rs.getString("KategoriAdi");
                String aciklama = rs.getString("Aciklama");

                Kategoriler kategori = new Kategoriler(kategoriID, kategoriAdi, aciklama);
                kategorilerList.add(kategori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategorilerList;
    }

    public Kategoriler selectKategori(int id) {
        Kategoriler kategori = null;
        String query = "SELECT * FROM Kategoriler WHERE KategoriID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Set the parameter for the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Check if the ResultSet contains any data
                    kategori = new Kategoriler(
                            rs.getInt("KategoriID"),
                            rs.getString("KategoriAdi"),
                            rs.getString("Aciklama"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategori;
    }

    public void insertKategori(Kategoriler kategori) {
        String query = "INSERT INTO Kategoriler (KategoriID, KategoriAdi, Aciklama) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, kategori.getKategoriID());
            stmt.setString(2, kategori.getKategoriAdi());
            stmt.setString(3, kategori.getAciklama());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateKategori(Kategoriler kategori) {
        String query = "UPDATE Kategoriler SET KategoriAdi = ?, Aciklama = ? WHERE KategoriID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, kategori.getKategoriAdi());
            stmt.setString(2, kategori.getAciklama());
            stmt.setInt(3, kategori.getKategoriID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKategori(int id) {
        String query = "DELETE FROM Kategoriler WHERE KategoriID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
