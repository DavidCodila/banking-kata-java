import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount, LocalDate date) throws InvalidParameterException {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not deposit less than 1");
        }
        this.balance += amount;
        transactions.add(new Transaction(amount, balance, TransactionType.DEPOSIT.symbol, date));
    }

    public void withdraw(int amount, LocalDate date) throws RuntimeException {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not withdraw less than 1");
        }
        if (balance - amount < 0) {
            throw new RuntimeException("Invalid funds to make transaction");
        }
        this.balance -= amount;
        transactions.add(new Transaction(amount, balance, TransactionType.WITHDRAWAL.symbol, date));
    }

    public String printStatement() {
        return Constants.statementHeader + getTransactions();
    }

    private String getTransactions() {
        StringBuilder transactionsString = new StringBuilder();
        for (Transaction transaction : transactions) {
            transactionsString.append(transaction.getInformation()).append("\n");
        }
        return transactionsString.toString().stripTrailing();
    }

}
