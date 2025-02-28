import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionFilter {

    private List<Transaction> transactions = new ArrayList<>();
    private Predicate<Transaction> streamPredicate;

    public void loadTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getAllTransactions() {
        this.streamPredicate = transaction -> true;
        return filteredTransactions();
    }

    public String getTransactionsByAmount(int amount) {
        this.streamPredicate = transaction -> transaction.amount() == amount;
        return filteredTransactions();
    }

    public String getTransactionsByDate(LocalDate date) {
        this.streamPredicate = transaction -> transaction.date() == date;
        return filteredTransactions();
    }

    private String filteredTransactions() {
        return this.transactions.stream()
                .filter(streamPredicate)
                .map(TransactionType::getFormattedTransaction)
                .collect(Collectors.joining())
                .stripTrailing();
    }
}
