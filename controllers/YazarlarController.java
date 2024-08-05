package controllers;

import model.Yazarlar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YazarlarController {
    private Connection connection;

    public YazarlarController(Connection connection) {
        this.connection = connection;
    }

    public List<Yazarlar> selectYazarlar() {
        List<Yazarlar> yazarlarList = new ArrayList<>();
        String query = "SELECT * FROM Yazarlar";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int yazarID = rs.getInt("YazarID");
                String ad = rs.getString("Ad");
                String soyad = rs.getString("Soyad");
                String biyografi = rs.getString("Biyografi");

                Yazarlar yazar = new Yazarlar(yazarID, ad, soyad, biyografi);
                yazarlarList.add(yazar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yazarlarList;
    }

    public Yazarlar selectYazar(int id) {
        Yazarlar yazar = null;
        String query = "SELECT * FROM Yazarlar WHERE YazarID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Set the parameter for the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Check if the ResultSet contains any data
                    yazar = new Yazarlar(
                            rs.getInt("YazarID"),
                            rs.getString("Ad"),
                            rs.getString("Soyad"),
                            rs.getString("Biyografi"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yazar;
    }

    public void insertYazar(Yazarlar yazar) {
        String query = "INSERT INTO Yazarlar (YazarID, Ad, Soyad, Biyografi) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, yazar.getYazarID());
            stmt.setString(2, yazar.getAd());
            stmt.setString(3, yazar.getSoyad());
            stmt.setString(4, yazar.getBiyografi());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateYazar(Yazarlar yazar) {
        String query = "UPDATE Yazarlar SET Ad = ?, Soyad = ?, Biyografi = ? WHERE YazarID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, yazar.getAd());
            stmt.setString(2, yazar.getSoyad());
            stmt.setString(3, yazar.getBiyografi());
            stmt.setInt(4, yazar.getYazarID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteYazar(int id) {
        String query = "DELETE FROM Yazarlar WHERE YazarID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
