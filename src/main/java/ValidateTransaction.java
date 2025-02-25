import java.security.InvalidParameterException;

public class ValidateTransaction {

    public static void validateDeposit(int amount) {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not deposit less than 1");
        }
    }

    public static void validateWithdrawal(int amount, int balance) {
        if (amount <= 0) {
            throw new InvalidParameterException("Can not withdraw less than 1");
        }
        if (balance - amount < 0) {
            throw new RuntimeException("Invalid funds to make transaction");
        }
    }
}
