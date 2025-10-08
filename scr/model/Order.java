package scr.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import scr.model.MetodoDePago.PaymentMethod;

public class Order {
    
    private int orderId;
    private List<Product> products;
    LocalDateTime buyDate;
    private PaymentMethod paymentMethod;

    public Order (int orderId, PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
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
    public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
    return paymentMethod;
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
public String showOrder(){
    String chekkouMessage= "";
    System.out.println("=== Resumen del Pedido ===");
    System.out.println("Pedido #" + orderId);

    for (Product p : products) {
        System.out.println(p.getName() + " - $" + p.getPrice());
    }
    LocalDateTime maxPaymentDate = buyDate.plusHours(24);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm", new Locale("es", "ES"));
    System.out.println("Total: $" + TotalCost() + " La fecha maxima de pago de su orden es: " + maxPaymentDate.format(formatter));
    
    chekkouMessage = "Total: $" + TotalCost() + " La fecha maxima de pago de su orden es: " + maxPaymentDate.format(formatter);
    return chekkouMessage;
}

public String processOrderPayment() {
    if (paymentMethod == null) {
        return "❌ No se ha seleccionado ningún método de pago.";
    }

    // Obtiene el mensaje de pago desde el método de pago específico
    String paymentMessage = paymentMethod.processPayment();

    // También puedes conservar esta línea si quieres mostrarlo en consola
    System.out.println("Procesando pago para el pedido #" + orderId + ":");
    System.out.println(paymentMessage);

    return "Procesando pago para el pedido #" + orderId + ":\n" + paymentMessage;
}

}