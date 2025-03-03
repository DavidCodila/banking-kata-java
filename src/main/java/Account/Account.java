package Account;

import Constants.*;
import Filter.*;
import Transaction.*;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {
    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount, LocalDate date) throws InvalidParameterException {
        validateAmount(amount);
        this.balance += amount;
        transactions.add(new Transaction(amount, this.balance, TransactionType.DEPOSIT, date));
    }

    public void withdraw(int amount, LocalDate date) throws RuntimeException {
        this.validateWithdrawal(amount);
        this.balance -= amount;
        this.transactions.add(new Transaction(amount, this.balance, TransactionType.WITHDRAWAL, date));
    }

    public String printStatement() {
        return printTransactions(this.transactions);
    }

    private String printTransactions(List<Transaction> transactions) {
        return Constants.statementHeader +
                transactions.stream()
                        .map(transaction -> transaction.type().getFormattedTransaction(transaction))
                        .collect(Collectors.joining())
                        .stripTrailing();
    }

    public List<Transaction> filter(Filter filter) {
        return this.transactions.stream()
                .filter(filter::apply).collect(Collectors.toList());
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new InvalidParameterException("Amount not be less than 1");
        }
    }

    private void validateWithdrawal(int amount) {
        this.validateAmount(amount);
        if (this.balance - amount < 0) {
            throw new RuntimeException("Invalid funds to make transaction");
        }
    }

}
