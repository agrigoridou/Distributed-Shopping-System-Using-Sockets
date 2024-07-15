package common;

import java.io.Serializable;

public class PurchaseMessage implements Serializable {
    private String productCode;
    private int quantity;

    public PurchaseMessage(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }
}
