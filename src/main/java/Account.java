import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Account {
    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount, LocalDate date) throws InvalidParameterException {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not deposit less than 1");
        }
        this.balance += amount;
        transactions.add(new Transaction(amount, this.balance, TransactionType.DEPOSIT, date));
    }

    public void withdraw(int amount, LocalDate date) throws RuntimeException {
        this.validateWithdrawal(amount);
        this.balance -= amount;
        this.transactions.add(new Transaction(amount, this.balance, TransactionType.WITHDRAWAL, date));
    }

    public String printStatement() {
        return this.filteredTransactions(transaction -> true);
    }

    public String printStatementByAmount(int amount) {
        return this.filteredTransactions(transaction -> transaction.amount() == amount);
    }

    public String printStatementByDate(LocalDate date) {
        return this.filteredTransactions(transaction -> transaction.date() == date);
    }

    public String printWithdrawalTransactions() {
        return this.filteredTransactions(transaction -> transaction.type() == TransactionType.WITHDRAWAL);
    }

    public String printDepositTransactions() {
        return this.filteredTransactions(transaction -> transaction.type() == TransactionType.DEPOSIT);
    }

    private String filteredTransactions(Predicate<Transaction> streamPredicate) {
        return Constants.statementHeader +
                this.transactions.stream()
                        .filter(streamPredicate)
                        .map(transaction -> transaction.type().getFormattedTransaction(transaction))
                        .collect(Collectors.joining())
                        .stripTrailing();
    }

    private void validateWithdrawal(int amount) {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not withdraw less than 1");
        }
        if (this.balance - amount < 0) {
            throw new RuntimeException("Invalid funds to make transaction");
        }
    }

}
