import java.time.LocalDate;

public class Transaction {
    private final int amount;
    private final int balance;
    private final String type;
    private final LocalDate date;

    public Transaction(int amount, int balance, String type, LocalDate date) {
        this.amount = amount;
        this.balance = balance;
        this.type = type;
        this.date = date;
    }

    public String getInformation() {
        char amountPrefix = switch (type) {
            case Constants.withdrawalType -> '-';
            case Constants.depositType -> '+';
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        return date + "\t" + amountPrefix + amount + "\t" + balance;
    }
}
