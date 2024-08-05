package filters;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class CORSFilter extends Filter {

  @Override
  public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");

    if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
      exchange.sendResponseHeaders(204, -1); // No Content for preflight requests
    } else {
      chain.doFilter(exchange);
    }
  }

  @Override
  public String description() {
    return "Add CORS headers to the response";
  }
}
