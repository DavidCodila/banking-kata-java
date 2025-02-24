import java.time.LocalDate;

public record Transaction(int amount, int balance, String symbol, LocalDate date) {
}
