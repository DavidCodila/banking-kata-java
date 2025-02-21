import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int balance;
    private List<Transaction> transactions;

    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) throws InvalidParameterException {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not deposit less than 1");
        }
        this.balance += amount;
    }

    public void withdraw(int amount) throws RuntimeException {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not withdraw less than 1");
        }
        if (balance - amount < 0) {
            throw new RuntimeException("Invalid funds to make transaction");
        }
        this.balance -= amount;
    }

}
