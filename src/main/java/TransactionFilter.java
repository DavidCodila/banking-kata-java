import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionFilter {

    public static String getAllTransactions(List<Transaction> transactions) {
        return filteredTransactions(transactions, transaction -> true);
    }

    public static String getTransactionsByAmount(List<Transaction> transactions, int amount) {
        return filteredTransactions(transactions, transaction -> transaction.amount() == amount);
    }

    public static String getTransactionsByType(List<Transaction> transactions, TransactionType type) {
        if (type == TransactionType.DEPOSIT) {
            return filteredTransactions(
                    transactions,
                    transaction -> Objects.equals(transaction.symbol(), TransactionType.DEPOSIT.symbol)
            );
        }
        else if (type == TransactionType.WITHDRAWAL) {
            return filteredTransactions(
                    transactions,
                    transaction -> Objects.equals(transaction.symbol(), TransactionType.WITHDRAWAL.symbol)
            );
        }
        throw new IllegalArgumentException(type.symbol);
    }

    public static String getTransactionsDate(List<Transaction> transactions, LocalDate date) {
        return filteredTransactions(transactions, transaction -> transaction.date() == date);
    }

    private static String filteredTransactions(List<Transaction> transactions, Predicate<Transaction> streamPredicate) {
        return transactions.stream()
                .filter(streamPredicate)
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
