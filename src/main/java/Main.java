import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        Account account = new Account();
        account.deposit(1000, date);
        account.deposit(200, date);
        date = date.plusDays(100);
        account.withdraw(300, date);
        account.withdraw(200, date);
        account.withdraw(100, date);
        System.out.println(account.printStatement() + "\n");

        int amount = 200;
        System.out.println("Search by amount: " + amount);
        System.out.println(account.getTransactionsByAmount(amount));
    }
}
