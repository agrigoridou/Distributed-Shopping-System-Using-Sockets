package client;

import common.*;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean authenticated;
    private ProductListMessage productList;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authenticate(String username, String password) {
        try {
            out.writeObject(new AuthMessage(username, password));
            ResponseMessage response = (ResponseMessage) in.readObject();
            authenticated = response.getMessage().equals("Authentication Successful");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void requestProductList() {
        try {
            out.writeObject(new RequestMessage("GET_PRODUCTS"));
            productList = (ProductListMessage) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ProductListMessage getProductList() {
        return productList;
    }

    public void purchaseProduct(String productCode, int quantity) {
        try {
            out.writeObject(new PurchaseMessage(productCode, quantity));
            ResponseMessage response = (ResponseMessage) in.readObject();
            JOptionPane.showMessageDialog(null, response.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void purchaseProduct(PurchaseMessage purchaseMessage) {
    }
}
