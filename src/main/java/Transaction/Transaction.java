package Transaction;

import java.time.LocalDate;

public record Transaction(Amount amount, LocalDate date) {}
