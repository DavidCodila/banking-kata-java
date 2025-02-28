public enum TransactionType {
    WITHDRAWAL("-"),
    DEPOSIT("+");

    private final String symbol;

    TransactionType(String symbol) {
        this.symbol = symbol;
    }

    public String getFormattedTransaction(Transaction transaction) {
        return transaction.date()
                + "\t" + this.symbol + transaction.amount()
                + "\t" + transaction.balance() + "\n";
    }
}
