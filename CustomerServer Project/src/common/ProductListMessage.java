package common;


import java.io.Serializable;
import java.util.Map;

public class ProductListMessage implements Serializable {
    private Map<String, Integer> products;

    public ProductListMessage(Map<String, Integer> products) {
        this.products = products;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "ProductListMessage{" +
                "products=" + products +
                '}';
    }
}