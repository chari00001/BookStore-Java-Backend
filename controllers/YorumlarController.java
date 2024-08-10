package controllers;

import model.Yorumlar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class YorumlarController {
  private Connection connection;

  public YorumlarController(Connection connection) {
    this.connection = connection;
  }

  // Method to get comments by KitapID
  public List<Yorumlar> getCommentsByKitapID(int kitapID) {
    List<Yorumlar> yorumlarList = new ArrayList<>();
    String query = "SELECT * FROM Yorumlar WHERE KitapID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, kitapID);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        int yorumID = rs.getInt("YorumID");
        int musteriID = rs.getInt("MusteriID");
        int parentID = rs.getInt("ParentID");
        Timestamp tarih = rs.getTimestamp("Tarih");
        String yorum = rs.getString("Yorum");

        Yorumlar yorumObj = new Yorumlar(yorumID, musteriID, kitapID, parentID, tarih, yorum);
        yorumlarList.add(yorumObj);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return yorumlarList;
  }

  // Method to post a new comment
  public void postComment(Yorumlar yorum) {
    String query = "INSERT INTO Yorumlar (YorumID, MusteriID, KitapID, ParentID, Tarih, Yorum) VALUES (?, ?, ?, ?, ?, ?)";

    System.out.println(yorum);

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, yorum.getYorumID());
      stmt.setInt(2, yorum.getMusteriID());
      stmt.setInt(3, yorum.getKitapID());
      stmt.setInt(4, yorum.getParentID());
      stmt.setTimestamp(5, yorum.getTarih());
      stmt.setString(6, yorum.getYorum());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Method to delete a comment by YorumID
  public void deleteComment(int yorumID) {
    String query = "DELETE FROM Yorumlar WHERE YorumID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, yorumID);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Method to get comment replies by ParentID
  public List<Yorumlar> getCommentReplies(int parentID) {
    List<Yorumlar> repliesList = new ArrayList<>();
    String query = "SELECT * FROM Yorumlar WHERE ParentID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, parentID);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        int yorumID = rs.getInt("YorumID");
        int musteriID = rs.getInt("MusteriID");
        int kitapID = rs.getInt("KitapID");
        Timestamp tarih = rs.getTimestamp("Tarih");
        String yorum = rs.getString("Yorum");

        Yorumlar reply = new Yorumlar(yorumID, musteriID, kitapID, parentID, tarih, yorum);
        repliesList.add(reply);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return repliesList;
  }
}
