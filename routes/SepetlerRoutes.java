package routes;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import controllers.SepetlerController;
import model.Sepetler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

public class SepetlerRoutes implements HttpHandler {
  private Gson gson = new Gson();
  private SepetlerController sepetlerController;

  public SepetlerRoutes(Connection connection) {
    this.sepetlerController = new SepetlerController(connection);
  }

  private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
    exchange.sendResponseHeaders(statusCode, response.getBytes().length);
    try (OutputStream os = exchange.getResponseBody()) {
      os.write(response.getBytes());
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
    if (method.equals("GET")) {
      String path = exchange.getRequestURI().getPath();
      String[] pathParts = path.split("/");

      if (pathParts.length == 3 && pathParts[2].matches("\\d+")) {
        handleGetById(exchange);
      } else {
        handleGet(exchange);
      }
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
    List<Sepetler> carts = sepetlerController.selectSepetler();
    sendJsonResponse(exchange, 200, carts);
  }

  private void handleGetById(HttpExchange exchange) throws IOException {
    Integer id = Integer.parseInt(exchange.getRequestURI().getPath().split("/")[2]);
    Sepetler cart = sepetlerController.selectSepet(id);
    sendJsonResponse(exchange, 200, cart);
  }

  private void handlePost(HttpExchange exchange) throws IOException {
    Sepetler newCart = gson.fromJson(new InputStreamReader(exchange.getRequestBody()), Sepetler.class);
    sepetlerController.insertSepet(newCart);
    String response = "Kitap inserted successfully";
    sendResponse(exchange, 200, response);
  }

  private void handlePut(HttpExchange exchange) throws IOException {
    Sepetler updatedBook = gson.fromJson(new InputStreamReader(exchange.getRequestBody()), Sepetler.class);
    sepetlerController.updateSepet(updatedBook);
    String response = "Kitap updated successfully";
    sendResponse(exchange, 200, response);
  }

  private void handleDelete(HttpExchange exchange) throws IOException {
    String query = exchange.getRequestURI().getQuery();
    int id = Integer.parseInt(query.split("=")[1]);
    sepetlerController.deleteSepet(id);
    String response = "Sepet deleted successfully";
    sendResponse(exchange, 200, response);
  }
}
