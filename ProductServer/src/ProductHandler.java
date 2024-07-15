import java.io.*;
import java.net.*;
import java.util.*;
import common.*;

public class ProductHandler extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Map<String, Integer> products;

    public ProductHandler(Socket socket, Map<String, Integer> products) {
        this.socket = socket;
        this.products = products;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Object message;

            while ((message = in.readObject()) != null) {
                if (message instanceof RequestMessage) {
                    handleRequest((RequestMessage) message);
                } else if (message instanceof PurchaseMessage) {
                    handlePurchase((PurchaseMessage) message);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(Object message) throws IOException {
        if (message instanceof RequestMessage) {
            RequestMessage request = (RequestMessage) message;
            if (request.getRequestType().equals("GET_PRODUCTS")) {
                out.writeObject(new ProductListMessage(products));
            }
        } else if (message instanceof PurchaseMessage) {
            handlePurchase((PurchaseMessage) message);
        }
    }


    private void handlePurchase(PurchaseMessage message) throws IOException {
        String productCode = message.getProductCode();
        int quantity = message.getQuantity();

        if (products.containsKey(productCode) && products.get(productCode) >= quantity) {
            products.put(productCode, products.get(productCode) - quantity);
            out.writeObject(new ResponseMessage("Purchase Successful"));
        } else {
            out.writeObject(new ResponseMessage("Purchase Failed: Insufficient Stock"));
        }
    }
}