import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAccount {
    private static final LocalDate date = LocalDate.now();
    private Account account;

    @BeforeEach
    public void setup() {
        account = new Account();
    }

    @Test
    public void testAccountDeposit_1() {
        account.deposit(1, date);
        assertEquals(1, account.getBalance());
    }

    @Test
    public void testAccountDeposit_100_200() {
        account.deposit(100, date);
        account.deposit(200, date);
        assertEquals(300, account.getBalance());
    }

    @Test
    public void testAccountWithdraw_1() {
        account.deposit(2, date);
        account.withdraw(1, date);
        assertEquals(1, account.getBalance());
    }

    @Test
    public void testAccountWithdraw_100_200() {
        account.deposit(400, date);
        account.withdraw(100, date);
        account.withdraw(200, date);
        assertEquals(100, account.getBalance());
    }

    @Test
    public void testAccountPrintStatement_Deposit1() {
        account.deposit(1, date);
        assertEquals(
                Constants.statementHeader + date + "\t+1\t1",
                account.printStatement()
        );
    }

    @Test
    public void testAccountWithdraw_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.withdraw(0, date)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testAccountWithdraw_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.withdraw(-1, date)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testAccountWithdraw_InvalidFunds() {
        account.deposit(1, date);
        var exception = assertThrows(
                RuntimeException.class,
                () -> account.withdraw(2, date)
        );
        assertEquals(exception.getMessage(), "Invalid funds to make transaction");
    }

    @Test
    public void testAccountDeposit_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.deposit(0, date)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }

    @Test
    public void testAccountDeposit_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.deposit(-1, date)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }
}
