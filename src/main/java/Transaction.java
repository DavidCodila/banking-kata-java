import java.time.LocalDate;

public class Transaction {
    private final int amount;
    private final int balance;
    private final String symbol;
    private final LocalDate date;

    public Transaction(int amount, int balance, String symbol, LocalDate date) {
        this.amount = amount;
        this.balance = balance;
        this.symbol = symbol;
        this.date = date;
    }

    public String getInformation() {
        return date + "\t" + symbol + amount + "\t" + balance;
    }
}
