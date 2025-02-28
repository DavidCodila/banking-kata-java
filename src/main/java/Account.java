import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int balance;
    private final List<Transaction> transactions;
    private final TransactionFilter filter;

    public Account(TransactionFilter filter) {
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.filter = filter;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount, LocalDate date) throws InvalidParameterException {
        ValidateTransaction.validateDeposit(amount);
        this.balance += amount;
        transactions.add(new Transaction(amount, this.balance, TransactionType.DEPOSIT, date));
    }

    public void withdraw(int amount, LocalDate date) throws RuntimeException {
        ValidateTransaction.validateWithdrawal(amount, this.balance);
        this.balance -= amount;
        this.transactions.add(new Transaction(amount, this.balance, TransactionType.WITHDRAWAL, date));
    }

    public String printStatement() {
        this.filter.loadTransactions(this.transactions);
        return Constants.statementHeader + this.filter.getAllTransactions();
    }

    public String getTransactionsByAmount(int amount) {
        this.filter.loadTransactions(this.transactions);
        return Constants.statementHeader + this.filter.getTransactionsByAmount(amount);
    }

    public String getTransactionsByDate(LocalDate date) {
        this.filter.loadTransactions(this.transactions);
        return Constants.statementHeader + this.filter.getTransactionsByDate(date);
    }

}
