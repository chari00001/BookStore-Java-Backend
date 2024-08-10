import com.sun.net.httpserver.HttpServer;
import src.DatabaseConnection;
import routes.KitaplarRoutes;
import routes.YazarlarRoutes;
import routes.KategorilerRoutes;
import routes.MusterilerRoutes;
import routes.YayinevleriRoutes;
import routes.SepetlerRoutes;
import routes.YorumlarRoutes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;

import filters.CORSFilter;

public class Main {
    public static void main(String[] args) throws IOException {

        DatabaseConnection db = new DatabaseConnection();
        db.connect(db.getJdbcUrl(), db.getUsername(), db.getPassword());
        Connection connection = db.getConnection();
        HttpServer server = HttpServer.create(new InetSocketAddress(3001), 0);

        server.createContext("/kitaplar", new KitaplarRoutes(connection)).getFilters().add(new CORSFilter());
        server.createContext("/yazarlar", new YazarlarRoutes(connection)).getFilters().add(new CORSFilter());
        server.createContext("/kategoriler", new KategorilerRoutes(connection)).getFilters().add(new CORSFilter());
        server.createContext("/musteriler", new MusterilerRoutes(connection)).getFilters().add(new CORSFilter());
        server.createContext("/login", new MusterilerRoutes(connection)).getFilters().add(new CORSFilter());
        server.createContext("/register", new MusterilerRoutes(connection)).getFilters().add(new CORSFilter());
        server.createContext("/yayinevleri", new YayinevleriRoutes(connection)).getFilters().add(new CORSFilter());
        server.createContext("/sepetler", new SepetlerRoutes(connection)).getFilters().add(new CORSFilter());
        server.createContext("/yorumlar", new YorumlarRoutes(connection)).getFilters().add(new CORSFilter());

        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 3001");
    }
}
