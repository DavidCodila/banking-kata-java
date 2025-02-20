import java.security.InvalidParameterException;

public class Account {
    private int balance;

    public Account() {
        this.balance = 0;
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
}
