package controllers;

import model.Yayinevleri;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YayinevleriController {
    private Connection connection;

    public YayinevleriController(Connection connection) {
        this.connection = connection;
    }

    public List<Yayinevleri> selectYayinevleri() {
        List<Yayinevleri> yayinevleriList = new ArrayList<>();
        String query = "SELECT * FROM Yayinevleri";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int yayineviID = rs.getInt("YayineviID");
                String yayineviAdi = rs.getString("YayineviAdi");
                String adres = rs.getString("Adres");
                String telefonNumarasi = rs.getString("TelefonNumarasi");
                String eposta = rs.getString("Eposta");

                Yayinevleri yayinevi = new Yayinevleri(yayineviID, yayineviAdi, adres, telefonNumarasi, eposta);
                yayinevleriList.add(yayinevi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yayinevleriList;
    }

    public Yayinevleri selectYayinevi(int id) {
        Yayinevleri yayinevi = null;
        String query = "SELECT * FROM Yayinevleri WHERE YayineviID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Set the parameter for the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Check if the ResultSet contains any data
                    yayinevi = new Yayinevleri(
                            rs.getInt("YayineviID"),
                            rs.getString("YayineviAdi"),
                            rs.getString("Adres"),
                            rs.getString("TelefonNumarasi"),
                            rs.getString("Eposta"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yayinevi;
    }

    public void insertYayinevi(Yayinevleri yayinevi) {
        String query = "INSERT INTO Yayinevleri (YayineviID, YayineviAdi, Adres, TelefonNumarasi, Eposta) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, yayinevi.getYayineviID());
            stmt.setString(2, yayinevi.getYayineviAdi());
            stmt.setString(3, yayinevi.getAdres());
            stmt.setString(4, yayinevi.getTelefonNumarasi());
            stmt.setString(5, yayinevi.getEposta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateYayinevi(Yayinevleri yayinevi) {
        String query = "UPDATE Yayinevleri SET YayineviAdi = ?, Adres = ?, TelefonNumarasi = ?, Eposta = ? WHERE YayineviID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, yayinevi.getYayineviAdi());
            stmt.setString(2, yayinevi.getAdres());
            stmt.setString(3, yayinevi.getTelefonNumarasi());
            stmt.setString(4, yayinevi.getEposta());
            stmt.setInt(5, yayinevi.getYayineviID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteYayinevi(int id) {
        String query = "DELETE FROM Yayinevleri WHERE YayineviID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
