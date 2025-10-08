package scr.model.MetodoDePago;

public class DigitalWallet extends PaymentMethod {

    private int walletID;

    public DigitalWallet(String ownerName, double amount, int walletID) {
        super(ownerName, amount);
        this.walletID = walletID;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }


//Metodos
    @Override
    public String processPayment() {
        return "Procesando billetera digital con ID comenzando en " + String.valueOf(walletID).substring(0, 4);
    }
}


