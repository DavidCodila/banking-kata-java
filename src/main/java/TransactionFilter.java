import java.util.List;
import java.util.Objects;

public class TransactionFilter {

    public static String getAllTransactions(List<Transaction> transactions) {
        StringBuilder transactionsString = new StringBuilder();
        for (Transaction transaction : transactions) {
            transactionsString.append(formatTransaction(transaction)).append("\n");
        }
        return transactionsString.toString().stripTrailing();
    }

    public static String getTransactionsByAmount(List<Transaction> transactions, int amount) {
        StringBuilder transactionsString = new StringBuilder();
        for (Transaction transaction: transactions) {
            if (transaction.amount() == amount) {
                transactionsString.append(formatTransaction(transaction)).append("\n");
            }
        }
        return transactionsString.toString().stripTrailing();
    }

    public static String getTransactionsByType(List<Transaction> transactions, TransactionType type) {
        StringBuilder transactionsString = new StringBuilder();
        for (Transaction transaction: transactions) {
            if (type == TransactionType.WITHDRAWAL && Objects.equals(transaction.symbol(), "-")) {
                transactionsString.append(formatTransaction(transaction)).append("\n");
            }
            else if (type == TransactionType.DEPOSIT && Objects.equals(transaction.symbol(), "+")) {
                transactionsString.append(formatTransaction(transaction)).append("\n");
            }
        }
        return transactionsString.toString().stripTrailing();
    }

    private static String formatTransaction(Transaction transaction) {
        return transaction.date()
                + "\t" + transaction.symbol() + transaction.amount()
                + "\t" + transaction.balance();
    }
}
