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
        ValidateTransaction.validateDeposit(amount);
        this.balance += amount;
        transactions.add(new Transaction(amount, balance, TransactionType.DEPOSIT.symbol, date));
    }

    public void withdraw(int amount, LocalDate date) throws RuntimeException {
        ValidateTransaction.validateWithdrawal(amount, balance);
        this.balance -= amount;
        transactions.add(new Transaction(amount, balance, TransactionType.WITHDRAWAL.symbol, date));
    }

    public String printStatement() {
        return Constants.statementHeader + TransactionFilter.getAllTransactions(transactions);
    }

    public String getTransactionsByAmount(int amount) {
        return Constants.statementHeader + TransactionFilter.getTransactionsByAmount(transactions, amount);
    }

    public String getTransactionsByDate(LocalDate date) {
        return Constants.statementHeader + TransactionFilter.getTransactionsDate(transactions, date);
    }

}
