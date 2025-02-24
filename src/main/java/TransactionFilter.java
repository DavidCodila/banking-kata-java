import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionFilter {

    public static String getAllTransactions(List<Transaction> transactions) {
        return formatTransactions(transactions);
    }

    public static String getTransactionsByAmount(List<Transaction> transactions, int amount) {
        Predicate<Transaction> streamPredicate = item -> item.amount() == amount;
        return formatTransactions(transactions.stream().filter(streamPredicate).collect(Collectors.toList()));
    }

    public static String getTransactionsByType(List<Transaction> transactions, TransactionType type) {
        Predicate<Transaction> streamPredicate;
        if (type == TransactionType.DEPOSIT) {
            streamPredicate = item -> Objects.equals(item.symbol(), TransactionType.DEPOSIT.symbol);
        }
        else if (type == TransactionType.WITHDRAWAL) {
            streamPredicate = item -> Objects.equals(item.symbol(), TransactionType.WITHDRAWAL.symbol);
        }
        else {
            return "";
        }
        return formatTransactions(transactions.stream().filter(streamPredicate).collect(Collectors.toList()));
    }

    private static String formatTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionFilter::formatTransaction)
                .collect(Collectors.joining())
                .stripTrailing();
    }
    private static String formatTransaction(Transaction transaction) {
        return transaction.date()
                + "\t" + transaction.symbol() + transaction.amount()
                + "\t" + transaction.balance() + "\n";
    }
}
