package routes;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controllers.MusterilerController;
import controllers.LoginController;
import model.Musteriler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class MusterilerRoutes implements HttpHandler {
  private Gson gson = new Gson();
  private MusterilerController musterilerController;
  private LoginController loginController;

  public MusterilerRoutes(Connection connection) {
    this.musterilerController = new MusterilerController(connection);
    this.loginController = new LoginController(connection);
  }

  private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
    exchange.getResponseHeaders().set("Content-Type", "text/plain");
    exchange.sendResponseHeaders(statusCode, response.getBytes().length);
    try (OutputStream os = exchange.getResponseBody()) {
      os.write(response.getBytes());
      os.flush();
    }
  }

  private void sendJsonResponse(HttpExchange exchange, int statusCode, Object data) throws IOException {
    String jsonResponse = gson.toJson(data);
    exchange.getResponseHeaders().set("Content-Type", "application/json");
    sendResponse(exchange, statusCode, jsonResponse);
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    String method = exchange.getRequestMethod();
    String path = exchange.getRequestURI().getPath();
    String[] pathParts = path.split("/");

    if (path.equals("/login") && method.equals("POST")) {
      handleLogin(exchange);
    } else if (path.equals("/register") && method.equals("POST")) {
      handleRegister(exchange);
    } else if (method.equals("GET")) {
      if (pathParts.length == 3 && pathParts[2].matches("\\d+")) {
        handleGetById(exchange);
      }
      handleGet(exchange);
    } else if (method.equals("POST")) {
      handlePost(exchange);
    } else if (method.equals("PUT")) {
      handlePut(exchange);
    } else if (method.equals("DELETE")) {
      handleDelete(exchange);
    } else {
      sendResponse(exchange, 405, "Method not allowed");
    }
  }

  private void handleGet(HttpExchange exchange) throws IOException {
    List<Musteriler> customers = musterilerController.selectMusteriler();
    sendJsonResponse(exchange, 200, customers);
  }

  private void handleGetById(HttpExchange exchange) throws IOException {
    int id = Integer.parseInt(exchange.getRequestURI().getPath().split("/")[2]);

    System.out.println("ID: " + id);

    Musteriler customer = musterilerController.selectMusteri(id);
    sendJsonResponse(exchange, 200, customer);
  }

  private void handlePost(HttpExchange exchange) throws IOException {
    Musteriler newCustomer = gson.fromJson(new InputStreamReader(exchange.getRequestBody()), Musteriler.class);
    musterilerController.insertMusteri(newCustomer);
    String response = "Musteri inserted successfully";
    sendResponse(exchange, 200, response);
  }

  private void handlePut(HttpExchange exchange) throws IOException {
    Musteriler updatedCustomer = gson.fromJson(new InputStreamReader(exchange.getRequestBody()), Musteriler.class);
    musterilerController.updateMusteri(updatedCustomer);
    String response = "Musteri updated successfully";
    sendResponse(exchange, 200, response);
  }

  private void handleDelete(HttpExchange exchange) throws IOException {
    String query = exchange.getRequestURI().getQuery();
    int id = Integer.parseInt(query.split("=")[1]);
    musterilerController.deleteMusteri(id);
    String response = "Musteri deleted successfully";
    sendResponse(exchange, 200, response);
  }

  private void handleLogin(HttpExchange exchange) throws IOException {
    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
    StringBuilder rawBody = new StringBuilder();
    int c;
    while ((c = isr.read()) != -1) {
      rawBody.append((char) c);
    }
    System.out.println("Raw Request Body: " + rawBody.toString());

    try {
      Type type = new TypeToken<Map<String, String>>() {
      }.getType();
      Map<String, String> credentials = gson.fromJson(rawBody.toString(), type);

      String Eposta = credentials.get("Eposta");
      String Sifre = credentials.get("Sifre");

      boolean authenticated = loginController.authenticate(Eposta, Sifre);
      if (authenticated) {
        Musteriler user = musterilerController.selectMusteriByEposta(Eposta);
        System.out.println("User authenticated successfully");
        sendJsonResponse(exchange, 200, user);
      } else {
        System.out.println("Invalid MusteriID or Sifre");
        sendResponse(exchange, 401, "Invalid MusteriID or Sifre");
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid MusteriID format");
      sendResponse(exchange, 400, "Invalid MusteriID format");
    } catch (Exception e) {
      e.printStackTrace();
      sendResponse(exchange, 500, "Internal Server Error");
    }
  }

  private void handleRegister(HttpExchange exchange) throws IOException {
    if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
      try {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        Map<String, String> requestBody = gson.fromJson(isr, new TypeToken<Map<String, String>>() {
        }.getType());

        String Ad = requestBody.get("Ad");
        String Soyad = requestBody.get("Soyad");
        String Eposta = requestBody.get("Eposta");
        String Sifre = requestBody.get("Sifre");
        String Adres = requestBody.get("Adres");
        String TelefonNumarasi = requestBody.get("TelefonNumarasi");

        Musteriler newUser = new Musteriler(0, Ad, Soyad, Eposta, Sifre, Adres, TelefonNumarasi, 0);
        musterilerController.insertMusteri(newUser);

        sendResponse(exchange, 200, "Registration successful");
      } catch (Exception e) {
        e.printStackTrace();
        sendResponse(exchange, 500, "Internal Server Error");
      }
    } else {
      sendResponse(exchange, 405, "Method Not Allowed");
    }
  }

}
