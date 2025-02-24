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
    public void testDeposit_1() {
        account.deposit(1, date);
        assertEquals(1, account.getBalance());
    }

    @Test
    public void testDeposit_100_200() {
        account.deposit(100, date);
        account.deposit(200, date);
        assertEquals(300, account.getBalance());
    }

    @Test
    public void testWithdraw_1() {
        account.deposit(2, date);
        account.withdraw(1, date);
        assertEquals(1, account.getBalance());
    }

    @Test
    public void testWithdraw_100_200() {
        account.deposit(400, date);
        account.withdraw(100, date);
        account.withdraw(200, date);
        assertEquals(100, account.getBalance());
    }

    @Test
    public void testPrintStatement() {
        account.deposit(1, date);
        account.deposit(2, date);
        account.withdraw(3, date);
        String expectedOutput
                = Constants.statementHeader
                + date + "\t+1\t1" + "\n"
                + date + "\t+2\t3" + "\n"
                + date + "\t-3\t0";
        assertEquals(expectedOutput, account.printStatement());
    }

    @Test
    public void testGetTransactionsByAmount() {
        int amount = 3;
        account.deposit(10, date);
        account.deposit(amount, date);
        account.withdraw(1, date);
        account.withdraw(amount, date);
        account.withdraw(1, date);
        String expectedOutput =
                date + "\t+3\t13" + "\n" + date + "\t-3\t9";
        assertEquals(expectedOutput, account.getTransactionsByAmount(amount));
    }

    @Test
    public void testWithdraw_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.withdraw(0, date)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testWithdraw_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.withdraw(-1, date)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testWithdraw_InvalidFunds() {
        account.deposit(1, date);
        var exception = assertThrows(
                RuntimeException.class,
                () -> account.withdraw(2, date)
        );
        assertEquals(exception.getMessage(), "Invalid funds to make transaction");
    }

    @Test
    public void testDeposit_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.deposit(0, date)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }

    @Test
    public void testDeposit_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.deposit(-1, date)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }
}
