package au.com.auspost.account;

import au.com.auspost.constants.Constants;
import au.com.auspost.filter.Filter;
import au.com.auspost.transaction.Transaction;
import au.com.auspost.transaction.TransactionType;

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
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, date));
    }

    public void withdraw(int amount, LocalDate date) throws RuntimeException {
        this.validateWithdrawal(amount);
        this.balance -= amount;
        this.transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, date));
    }

    public List<Transaction> filter(Filter filter) {
        return this.transactions.stream()
                .filter(filter::apply).collect(Collectors.toList());
    }

    public String printStatement() {
        int balance = 0;
        StringBuilder stringBuilder = new StringBuilder().append(Constants.statementHeader);
        for(Transaction transaction: this.transactions) {
            balance = this.calculateNewBalance(transaction, balance);
            stringBuilder.append(transaction.print(balance)).append("\n");
        }
        return stringBuilder.toString().stripTrailing();
    }

    private int calculateNewBalance(Transaction transaction, int oldBalance) {
        return switch (transaction.type()) {
            case TransactionType.DEPOSIT -> oldBalance + transaction.amount();
            case TransactionType.WITHDRAWAL -> oldBalance - transaction.amount();
        };
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
