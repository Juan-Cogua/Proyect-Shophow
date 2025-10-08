package scr.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import scr.model.Order;
import scr.model.Product;
import scr.model.ProductLoader;
import scr.model.Customer;
import scr.model.MetodoDePago.PaymentMethod;
import scr.model.MetodoDePago.Card;
import scr.model.MetodoDePago.BankTransfer;
import scr.model.MetodoDePago.DigitalWallet;


public class ShopNowGUI extends JFrame {
  
    private JList<String>productList;
    private DefaultListModel<String>ListModel;
    private JTextArea cartArea;
    private JComboBox<String> paymentCombo;
    private Order order;
    private List<Product> catalogo;
    private Customer  customer;

    public ShopNowGUI() {
        setTitle("ShopNow - Carrito de Compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cargar productos
        catalogo = ProductLoader.loadProducts("product.txt");
        customer = new Customer("Andres Vargas", "andrescamilo.vargas@uptc.edu.co");

        order = new Order(101,null);

        // Panel de catalogo
        ListModel = new DefaultListModel<>();
        for (Product p : catalogo) {
            ListModel.addElement(p.getName() + " - $" + p.getPrice());
        }
     
        productList = new JList<>(ListModel);
        JScrollPane scrollCatalog = new JScrollPane(productList);

        JButton addButton = new JButton("Agregar al Carrito");
        addButton.addActionListener((ActionEvent e) -> {
            int selectProduct = productList.getSelectedIndex();
            if (selectProduct != -1) {
                Product p = catalogo.get(selectProduct);
                order.addProduct(p);
                cartArea.append(p.getName() + " - $" + p.getPrice() + "\n");
            }
        });

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Catálogo de Productos"), BorderLayout.NORTH);
        leftPanel.add(scrollCatalog, BorderLayout.CENTER); 
        leftPanel.add(addButton, BorderLayout.SOUTH);

        // Panel de carrito
        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane scrollCarrito = new JScrollPane(cartArea);

        // Combo de métodos de pago
        paymentCombo = new JComboBox<>(new String[]{"Tarjeta", "Transferencia Bancaria", "Billetera Digital"});
        JPanel paymentPanel = new JPanel();
        paymentPanel.add(new JLabel("Método de Pago:"));
        paymentPanel.add(paymentCombo);

        JButton checkoutButton = new JButton("Finalizar Compra");
        checkoutButton.addActionListener((ActionEvent e) -> {
            // Elegir método de pago
            String selected = (String) paymentCombo.getSelectedItem();
            String nombre = JOptionPane.showInputDialog(this, "Nombre del titular:");
            double monto = order.TotalCost();

PaymentMethod method = null;
try {
    if (selected.equals("Tarjeta")) {
        String cardNumberStr = JOptionPane.showInputDialog(this, "Número de tarjeta (solo números):");
        if (cardNumberStr == null || cardNumberStr.trim().isEmpty()) return;

        String cvvStr = JOptionPane.showInputDialog(this, "CVV:");
        if (cvvStr == null || cvvStr.trim().isEmpty()) return;

        String expiration = JOptionPane.showInputDialog(this, "Fecha de expiración (MM/AA):");
        if (expiration == null || expiration.trim().isEmpty()) return;

        int cardNumber = Integer.parseInt(cardNumberStr.replaceAll("\\s+", ""));
        int cvv = Integer.parseInt(cvvStr.trim());

        method = new Card(nombre, monto, cvv, cardNumber, expiration);

    } else if (selected.equals("Transferencia Bancaria")) {
        String accName = JOptionPane.showInputDialog(this, "Nombre de la cuenta:");
        String bankName = JOptionPane.showInputDialog(this, "Nombre del banco:");
        method = new BankTransfer(nombre, monto, accName, bankName);

    } else if (selected.equals("Billetera Digital")) {
        String walletIdStr = JOptionPane.showInputDialog(this, "ID de la billetera:");
        int walletId = Integer.parseInt(walletIdStr.trim());
        method = new DigitalWallet(nombre, monto, walletId);
    }
      if (method != null) {
            order.setPaymentMethod(method);

            // Procesa el pago igual que en main
            String paymentInfo = order.processOrderPayment();

            //  Muestra la orden y resultado del pago en el área de texto
            cartArea.append("\n------------------------\n");
            cartArea.append(order.showOrder() + "\n");
            cartArea.append(paymentInfo + "\n");
            cartArea.append("------------------------\n");
        }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número inválido. Intenta de nuevo.");
            return;
        }
            order.setPaymentMethod(method);
            order.processOrderPayment();
            cartArea.append("\n" + order.showOrder() + "\nMétodo de pago: " + selected + "\n");
        });

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Carrito de Compras"), BorderLayout.NORTH);
        rightPanel.add(scrollCarrito, BorderLayout.CENTER);
        rightPanel.add(paymentPanel, BorderLayout.NORTH);
        rightPanel.add(checkoutButton, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShopNowGUI().setVisible(true));
    }
}