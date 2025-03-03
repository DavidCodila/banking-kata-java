package Transaction;

import java.time.LocalDate;

public record Transaction(int amount, int balance, TransactionType type, LocalDate date) {}
