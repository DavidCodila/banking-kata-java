import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionFilter {

    public static String getAllTransactions(List<Transaction> transactions) {
        return filteredTransactions(transactions, transaction -> true);
    }

    public static String getTransactionsByAmount(List<Transaction> transactions, int amount) {
        return filteredTransactions(transactions, transaction -> transaction.amount() == amount);
    }

    public static String getTransactionsByDate(List<Transaction> transactions, LocalDate date) {
        return filteredTransactions(transactions, transaction -> transaction.date() == date);
    }

    private static String filteredTransactions(List<Transaction> transactions, Predicate<Transaction> streamPredicate) {
        return transactions.stream()
                .filter(streamPredicate)
                .map(TransactionType::getFormattedTransaction)
                .collect(Collectors.joining())
                .stripTrailing();
    }
}
