import java.io.*;
import java.net.*;
import java.util.*;

public class ProductServer {
    private ServerSocket serverSocket;
    private Map<String, Integer> products;

    public ProductServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            products = new HashMap<>();
            products.put("P001", 10); // Example product
            products.put("P002", 30);
            products.put("P003", 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                new ProductHandler(serverSocket.accept(), products).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}