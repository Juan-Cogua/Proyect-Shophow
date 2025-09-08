package scr.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Order {
    
    private int orderId;
    private List<Product> products;
    LocalDateTime buyDate;

    public Order (int orderId){
        this.orderId = orderId;
        this.buyDate = LocalDateTime.now();
        this.products = new ArrayList<>();
    }
    

    public int getOrderId() {
        return orderId;
    }


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public List<Product> getProducts() {
        return products;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public LocalDateTime getBuyDate() {
        return buyDate;
    }


    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
    }


    // Metodods 
    public void addProduct(Product newproduct){
        this.products.add(newproduct);
    }
    public double TotalCost(){
        double total = 0.0;
        for(Product product : products){
            total += product.getPrice();
        }
        return total;
    }
    public void showOrder(){
        System.out.println("=== Resumen del Pedido ===");
        System.out.println("Pedido #" + orderId);

        for (Product p : products) {
            System.out.println(" - " + p.getPrice());
        }
        LocalDateTime maxPaymentDate = buyDate.plusHours(24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm", new Locale("es", "ES"));
         System.out.println("Total: $" + TotalCost() + " La fecha maxima de pago de su orden es: " + maxPaymentDate.format(formatter));
    }
}