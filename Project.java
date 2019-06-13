import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.util.Map;
import java.sql.*;

//path %path%;C:\Program Files\Java\jdk1.8.0_131\bin
//javac -cp sqlite-jdbc-3.23.1.jar; Project.java

public class Project {
    public static void main(String[] args) throws IOException {

        Database db = new Database("jdbc:sqlite:Cartoon.db");
        int port = 8500;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            
        
       // String htmlFile = Input.readFile("JSONconnect.html");
		//server.createContext("/Server", new RouteHandler(htmlFile));

        String sql = "SELECT * FROM table1";
		server.createContext("/table1", new RouteHandler(db, sql));

        String sql2 = "SELECT * FROM table2";
		server.createContext("/table2", new RouteHandler(db, sql2));

        String sql3 = "SELECT * FROM table1 INNER JOIN table2 ON table1.Name = table2.Name";
		server.createContext("/alltable", new RouteHandler(db, sql3));


        server.start(); 
        System.out.println("Server is listening on port " + port );
    }    
}
