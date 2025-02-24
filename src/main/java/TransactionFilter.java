import java.util.List;

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

    private static String formatTransaction(Transaction transaction) {
        return transaction.date()
                + "\t" + transaction.symbol() + transaction.amount()
                + "\t" + transaction.balance();
    }
}
