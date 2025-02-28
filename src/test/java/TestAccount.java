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
        this.account = new Account(new TransactionFilter());
    }

    @Test
    public void testDeposit_1() {
        this.account.deposit(1, date);
        assertEquals(1, this.account.getBalance());
    }

    @Test
    public void testDeposit_100_200() {
        this.account.deposit(100, date);
        this.account.deposit(200, date);
        assertEquals(300, this.account.getBalance());
    }

    @Test
    public void testWithdraw_1() {
        this.account.deposit(2, date);
        this.account.withdraw(1, date);
        assertEquals(1, this.account.getBalance());
    }

    @Test
    public void testWithdraw_100_200() {
        this.account.deposit(400, date);
        this.account.withdraw(100, date);
        this.account.withdraw(200, date);
        assertEquals(100, this.account.getBalance());
    }

    @Test
    public void testPrintStatement() {
        this.account.deposit(1, date);
        this.account.deposit(2, date);
        this.account.withdraw(3, date);
        String expectedOutput
                = Constants.statementHeader
                + date + "\t+1\t1" + "\n"
                + date + "\t+2\t3" + "\n"
                + date + "\t-3\t0";
        assertEquals(expectedOutput, this.account.printStatement());
    }

    @Test
    public void testGetTransactionsByAmount() {
        int amount = 3;
        this.account.deposit(10, date);
        this.account.deposit(amount, date);
        this.account.withdraw(1, date);
        this.account.withdraw(amount, date);
        this.account.withdraw(1, date);
        String expectedOutput = Constants.statementHeader +
                date + "\t+3\t13" + "\n" + date + "\t-3\t9";
        assertEquals(expectedOutput, this.account.getTransactionsByAmount(amount));
    }

    @Test
    public void testGetTransactionsByDate() {
        LocalDate datePlus100Days = date.plusDays(100);
        this.account.deposit(10, date);
        this.account.deposit(200, datePlus100Days);
        this.account.withdraw(1, date);
        this.account.withdraw(100, date);
        this.account.withdraw(2, datePlus100Days);
        String expectedOutput = Constants.statementHeader +
                datePlus100Days + "\t+200\t210" + "\n" + datePlus100Days + "\t-2\t107";
        assertEquals(expectedOutput, this.account.getTransactionsByDate(datePlus100Days));
    }

    @Test
    public void testWithdraw_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> this.account.withdraw(0, date)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testWithdraw_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> this.account.withdraw(-1, date)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testWithdraw_InvalidFunds() {
        this.account.deposit(1, date);
        var exception = assertThrows(
                RuntimeException.class,
                () -> this.account.withdraw(2, date)
        );
        assertEquals(exception.getMessage(), "Invalid funds to make transaction");
    }

    @Test
    public void testDeposit_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> this.account.deposit(0, date)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }

    @Test
    public void testDeposit_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> this.account.deposit(-1, date)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }
}
