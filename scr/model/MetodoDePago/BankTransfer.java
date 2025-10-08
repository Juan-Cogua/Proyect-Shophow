package scr.model.MetodoDePago;

public class BankTransfer extends PaymentMethod {

    private String accName;
    private String bankName;

    public BankTransfer(String ownerName, double amount, String accName, String bankName) {
        super(ownerName, amount);
        this.accName = accName;
        this.bankName = bankName;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String processPayment() {
        return "Procesando transferencia bancaria desde el banco " + bankName +" a nombre de " + accName;
    }


}
