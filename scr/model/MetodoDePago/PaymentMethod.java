package scr.model.MetodoDePago;

public abstract class PaymentMethod {

    private String OwnerName;
    private double Amount;

    public PaymentMethod(String OwnerName, double Amount) {
        this.OwnerName = OwnerName;
        this.Amount = Amount;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    // Métodos
    public abstract String processPayment();




}