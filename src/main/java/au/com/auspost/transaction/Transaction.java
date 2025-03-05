package au.com.auspost.transaction;

import java.time.LocalDate;

public record Transaction(TransactionType type, int amount, LocalDate date) {
    public String print(int balance) {
        return this.type().getFormattedTransaction(this) + "\t" + balance;
    }
}
