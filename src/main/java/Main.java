import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Transaction transaction = new Transaction(2, 1, Constants.withdrawalType, LocalDate.now());
        System.out.println(transaction.getInformation());
    }
}
