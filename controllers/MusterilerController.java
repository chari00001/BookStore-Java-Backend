package controllers;

import model.Musteriler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusterilerController {
    private Connection connection;

    public MusterilerController(Connection connection) {
        this.connection = connection;
    }

    public List<Musteriler> selectMusteriler() {
        List<Musteriler> musterilerList = new ArrayList<>();
        String query = "SELECT * FROM Musteriler";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int musteriID = rs.getInt("MusteriID");
                String ad = rs.getString("Ad");
                String soyad = rs.getString("Soyad");
                String eposta = rs.getString("Eposta");
                String sifre = rs.getString("Sifre");
                String adres = rs.getString("Adres");
                String telefonNumarasi = rs.getString("TelefonNumarasi");
                int isAdmin = rs.getInt("isAdmin");

                Musteriler musteri = new Musteriler(musteriID, ad, soyad, eposta, sifre, adres, telefonNumarasi,
                        isAdmin);
                musterilerList.add(musteri);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musterilerList;
    }

    public Musteriler selectMusteri(int id) {
        String query = "SELECT * FROM Musteriler WHERE MusteriID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String ad = rs.getString("Ad");
                String soyad = rs.getString("Soyad");
                String eposta = rs.getString("Eposta");
                String sifre = rs.getString("Sifre");
                String adres = rs.getString("Adres");
                String telefonNumarasi = rs.getString("TelefonNumarasi");
                int isAdmin = rs.getInt("isAdmin");

                return new Musteriler(id, ad, soyad, eposta, sifre, adres, telefonNumarasi, isAdmin);
            } else {
                throw new SQLException("No customer found with the provided ID."); // Throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertMusteri(Musteriler musteri) {
        String sql = "INSERT INTO Musteriler (Ad, Soyad, Eposta, Sifre, Adres, TelefonNumarasi, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, musteri.getAd());
            statement.setString(2, musteri.getSoyad());
            statement.setString(3, musteri.getEposta());
            statement.setString(4, musteri.getSifre());
            statement.setString(5, musteri.getAdres());
            statement.setString(6, musteri.getTelefonNumarasi());
            statement.setInt(7, musteri.getIsAdmin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMusteri(Musteriler musteri) {
        String query = "UPDATE Musteriler SET Ad = ?, Soyad = ?, Eposta = ?, Sifre = ?, Adres = ?, TelefonNumarasi = ?, isAdmin = ? WHERE MusteriID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, musteri.getAd());
            stmt.setString(2, musteri.getSoyad());
            stmt.setString(3, musteri.getEposta());
            stmt.setString(4, musteri.getSifre());
            stmt.setString(5, musteri.getAdres());
            stmt.setString(6, musteri.getTelefonNumarasi());
            stmt.setInt(7, musteri.getIsAdmin());
            stmt.setInt(8, musteri.getMusteriID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMusteri(int id) {
        String query = "DELETE FROM Musteriler WHERE MusteriID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Musteriler selectMusteriByEposta(String eposta) throws SQLException {
        String query = "SELECT * FROM Musteriler WHERE Eposta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, eposta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int musteriID = rs.getInt("MusteriID");
                String ad = rs.getString("Ad");
                String soyad = rs.getString("Soyad");
                String sifre = rs.getString("Sifre");
                String adres = rs.getString("Adres");
                String telefonNumarasi = rs.getString("TelefonNumarasi");
                int isAdmin = rs.getInt("isAdmin");

                return new Musteriler(musteriID, ad, soyad, eposta, sifre, adres, telefonNumarasi, isAdmin);
            } else {
                throw new SQLException("No customer found with the provided email."); // Throw an exception
            }
        }
    }

}
