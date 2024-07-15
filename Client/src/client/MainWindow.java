package client;

import common.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private Client client;
    private JTextArea productListArea;
    private JTextField productCodeField;
    private JTextField quantityField;

    public MainWindow(Client client) {
        this.client = client;

        setTitle("Main Window");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        productListArea = new JTextArea();
        productListArea.setEditable(false);
        panel.add(new JScrollPane(productListArea), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(5, 2));

        controlPanel.add(new JLabel("Product Code:"));
        productCodeField = new JTextField();
        controlPanel.add(productCodeField);

        controlPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        controlPanel.add(quantityField);

        JButton refreshButton = new JButton("Refresh Products");
        refreshButton.addActionListener(new RefreshAction());
        controlPanel.add(refreshButton);

        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new PurchaseAction());
        controlPanel.add(purchaseButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);

        refreshProductList();
    }

    private void refreshProductList() {
        client.requestProductList();
        ProductListMessage productList = client.getProductList();
        productListArea.setText(productList.toString());
    }

    private class RefreshAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshProductList();
        }
    }

    private class PurchaseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String productCode = productCodeField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            client.purchaseProduct(productCode, quantity);
        }
    }
}