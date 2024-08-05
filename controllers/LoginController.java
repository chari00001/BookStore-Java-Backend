package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
  private Connection connection;

  public LoginController(Connection connection) {
    this.connection = connection;
  }

  public boolean authenticate(String Eposta, String Sifre) {
    String sql = "SELECT * FROM Musteriler WHERE Eposta = ? AND Sifre = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, Eposta);
      statement.setString(2, Sifre);

      ResultSet resultSet = statement.executeQuery();
      return resultSet.next(); // null ise hata ver
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

}
