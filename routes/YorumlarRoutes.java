package routes;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import controllers.YorumlarController;
import model.Yorumlar;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

public class YorumlarRoutes implements HttpHandler {
  private Gson gson = new Gson();
  private YorumlarController yorumlarController;

  public YorumlarRoutes(Connection connection) {
    this.yorumlarController = new YorumlarController(connection);
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
        handleGetByKitapID(exchange);
      } else if (pathParts.length == 4 && pathParts[2].equals("yanitlar")) {
        handleGetReplies(exchange);
      } else {
        sendResponse(exchange, 404, "Not Found");
      }
    } else if (method.equals("POST")) {
      handlePost(exchange);
    } else if (method.equals("DELETE")) {
      handleDelete(exchange);
    } else {
      sendResponse(exchange, 405, "Method not allowed");
    }
  }

  private void handleGetByKitapID(HttpExchange exchange) throws IOException {
    int id = Integer.parseInt(exchange.getRequestURI().getPath().split("/")[2]);
    List<Yorumlar> comments = yorumlarController.getCommentsByKitapID(id);
    System.out.println(comments);
    sendJsonResponse(exchange, 200, comments);
  }

  private void handleGetReplies(HttpExchange exchange) throws IOException {
    int parentID = Integer.parseInt(exchange.getRequestURI().getPath().split("/")[3]);
    List<Yorumlar> replies = yorumlarController.getCommentReplies(parentID);
    sendJsonResponse(exchange, 200, replies);
  }

  private void handlePost(HttpExchange exchange) throws IOException {
    Yorumlar newComment = new Gson().fromJson(new InputStreamReader(exchange.getRequestBody()), Yorumlar.class);

    yorumlarController.postComment(newComment);
    String response = "Yorum created successfully";
    sendResponse(exchange, 200, response);
  }

  private void handleDelete(HttpExchange exchange) throws IOException {
    int id = Integer.parseInt(exchange.getRequestURI().getPath().split("/")[2]);

    yorumlarController.deleteComment(id);
    String response = "Yorum deleted successfully";
    sendResponse(exchange, 200, response);
  }
}
