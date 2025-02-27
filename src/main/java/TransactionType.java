import java.util.List;

public enum TransactionType {
    WITHDRAWAL("-"),
    DEPOSIT("+");

    public final String symbol;

    TransactionType(String symbol) {
        this.symbol = symbol;
    }

    public static List<Transaction> getWithdrawalTransactions(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.type() == WITHDRAWAL).toList();
    }

    public static List<Transaction> getDepositTransactions(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.type() == DEPOSIT).toList();
    }

    public static String getFormattedTransaction(Transaction transaction) {
        String transactionSymbol = (transaction.type() == WITHDRAWAL) ? WITHDRAWAL.symbol : DEPOSIT.symbol;
        return transaction.date()
                + "\t" + transactionSymbol + transaction.amount()
                + "\t" + transaction.balance() + "\n";
    }
}
