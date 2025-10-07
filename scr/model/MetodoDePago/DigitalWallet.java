package scr.model.MetodoDePago;

public class DigitalWallet extends PaymentMethod {

    public DigitalWallet(String ownerName, double amount) {
        super(ownerName, amount);
    }

    @Override
    public void processPayment() {
        // Lógica para procesar pago con billetera digital
        System.out.println("Procesando pago con billetera digital...");
    }

}