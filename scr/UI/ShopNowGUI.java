package scr.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import scr.model.Order;
import scr.model.Product;
import scr.model.ProductLoader;
import scr.model.Customer;

public class ShopNowGUI extends JFrame {
  
    private JList<String>productList;
    private DefaultListModel<String>ListModel;
    private JTextArea cartArea;
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
        order = new Order(101);

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

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Carrito de Compras"), BorderLayout.NORTH);
        rightPanel.add(scrollCarrito, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Finalizar Compra");
        checkoutButton.addActionListener((ActionEvent e) -> {
          cartArea.append(order.showOrder());
        });
        rightPanel.add(checkoutButton, BorderLayout.SOUTH);
        //Dividri la ventana en dos partes
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          new ShopNowGUI().setVisible(true);
        });
    }

}
