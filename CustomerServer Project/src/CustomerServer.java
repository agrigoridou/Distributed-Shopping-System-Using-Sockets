import java.io.*;
import java.net.*;
import java.util.*;

public class CustomerServer {
    private ServerSocket serverSocket;
    private Map<String, String> users;

    public CustomerServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            users = new HashMap<>();
            users.put("user1", "password1"); // Example user
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                new ClientHandler(serverSocket.accept(), users).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}