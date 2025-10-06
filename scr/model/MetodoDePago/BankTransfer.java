package scr.model.MetodoDePago;

public class BankTransfer extends PaymentMethod {

    public BankTransfer(String ownerName, double amount) {
        super(ownerName, amount);
    }

    @Override
    public void processPayment() {
        // Lógica para procesar transferencia bancaria
        System.out.println("Procesando pago por transferencia bancaria...");
    }
}
