package scr.model.MetodoDePago;

public class Card extends PaymentMethod {

    public Card(String ownerName, double amount) {
        super(ownerName, amount);
    }

    @Override
    public void processPayment() {
        // Lógica para procesar pago con tarjeta
        System.out.println("Procesando pago con tarjeta...");
    }
  
}