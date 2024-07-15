import java.io.*;
import java.net.*;
import java.util.*;
import common.*;
public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Map<String, String> users;

    public ClientHandler(Socket socket, Map<String, String> users) {
        this.socket = socket;
        this.users = users;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Object message;

            while ((message = in.readObject()) != null) {
                if (message instanceof AuthMessage) {
                    handleAuth((AuthMessage) message);
                } else if (message instanceof RequestMessage) {
                    handleRequest((RequestMessage) message);
                } else if (message instanceof PurchaseMessage) {
                    handlePurchase((PurchaseMessage) message);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleAuth(AuthMessage message) throws IOException {
        if (users.containsKey(message.getUsername()) &&
                users.get(message.getUsername()).equals(message.getPassword())) {
            out.writeObject(new ResponseMessage("Authentication Successful"));
        } else {
            out.writeObject(new ResponseMessage("Authentication Failed"));
        }
    }

    private void handleRequest(RequestMessage message) throws IOException {
        if (message.getRequestType().equals("GET_PRODUCTS")) {
            try (Socket productSocket = new Socket("127.0.0.1", 6000);
                 ObjectOutputStream productOut = new ObjectOutputStream(productSocket.getOutputStream());
                 ObjectInputStream productIn = new ObjectInputStream(productSocket.getInputStream())) {

                productOut.writeObject(message);
                ProductListMessage productList = (ProductListMessage) productIn.readObject();
                out.writeObject(productList);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handlePurchase(PurchaseMessage message) throws IOException {
        try (Socket productSocket = new Socket("127.0.0.1", 6000);
             ObjectOutputStream productOut = new ObjectOutputStream(productSocket.getOutputStream());
             ObjectInputStream productIn = new ObjectInputStream(productSocket.getInputStream())) {

            productOut.writeObject(message);
            ResponseMessage response = (ResponseMessage) productIn.readObject();
            out.writeObject(response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}