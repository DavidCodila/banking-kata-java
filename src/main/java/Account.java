import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int balance;
    private final List<Transaction> transactions;
    private final LocalDate date;

    public Account(LocalDate date) {
        this.date = date;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) throws InvalidParameterException {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not deposit less than 1");
        }
        this.balance += amount;
        transactions.add(new Transaction(amount, balance, Constants.depositType, date));
    }

    public void withdraw(int amount) throws RuntimeException {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not withdraw less than 1");
        }
        if (balance - amount < 0) {
            throw new RuntimeException("Invalid funds to make transaction");
        }
        this.balance -= amount;
        transactions.add(new Transaction(amount, balance, Constants.withdrawalType, date));
    }

    public String printStatement() {
        StringBuilder transactionsString = new StringBuilder();
        for (Transaction transaction : transactions) {
            transactionsString.append(transaction.getInformation()).append("\n");
        }
        return Constants.statementHeader + transactionsString.toString().stripTrailing();
    }

}
