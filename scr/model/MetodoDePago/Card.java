package scr.model.MetodoDePago;

public class Card extends PaymentMethod {

    private int CVV;
    private int cardNumber;
    private String expirationDate;

    public Card(String ownerName, double amount, int CVV, int cardNumber, String expirationDate) {
        super(ownerName, amount);
        this.CVV = CVV;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

//Metodos
    @Override
    public String processPayment() {
      return "Procesando pago con tarjeta terminada en " + String.valueOf(cardNumber).substring(String.valueOf(cardNumber).length() - 4);
    }
}