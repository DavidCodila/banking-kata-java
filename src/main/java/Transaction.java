public class Transaction {
    private String date;
    private String amount;
    private int balance;

    public Transaction(String amount, int balance, String date) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public String getInformation() {
        return date + "\t" + amount + "\t" + balance;
    }
}
