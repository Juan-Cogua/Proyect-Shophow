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

        // Lista de productos
        ListModel = new DefaultListModel<>();
        for (Product p : catalogo) {
            ListModel.addElement(p.getName() + " - $" + p.getPrice());
        }
    
    }

}
