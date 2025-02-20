import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAccount {
    private Account account;

    @BeforeEach
    public void setup() {
        account = new Account();
    }

    @Test
    public void testAccountDeposit_1() {
        account.deposit(1);
        assertEquals(1, account.getBalance());
    }

    @Test
    public void testAccountDeposit_100_200() {
        account.deposit(100);
        account.deposit(200);
        assertEquals(300, account.getBalance());
    }

    @Test
    public void testAccountDeposit_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.deposit(0)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }

    @Test
    public void testAccountDeposit_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.deposit(-1)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }

    @Test
    public void testAccountWithdraw_1() {
        account.deposit(2);
        account.withdraw(1);
        assertEquals(1, account.getBalance());
    }

    @Test
    public void testAccountWithdraw_100_200() {
        account.deposit(400);
        account.withdraw(100);
        account.withdraw(200);
        assertEquals(100, account.getBalance());
    }

    @Test
    public void testAccountWithdraw_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.withdraw(0)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testAccountWithdraw_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.withdraw(-1)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testAccountWithdraw_InvalidFunds() {
        account.deposit(1);
        var exception = assertThrows(
                RuntimeException.class,
                () -> account.withdraw(2)
        );
        assertEquals(exception.getMessage(), "Invalid funds to make transaction");
    }
}
