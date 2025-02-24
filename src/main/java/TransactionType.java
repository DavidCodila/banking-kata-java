public enum TransactionType {
    WITHDRAWAL("-"),
    DEPOSIT("+");

    public final String symbol;

    TransactionType(String symbol) {
        this.symbol = symbol;
    }
}
