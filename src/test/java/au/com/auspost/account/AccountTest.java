package au.com.auspost.account;

import au.com.auspost.filter.AmountFilter;
import au.com.auspost.filter.DateFilter;
import au.com.auspost.filter.TypeFilter;
import au.com.auspost.transaction.Transaction;
import au.com.auspost.transaction.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {
    private static final LocalDate date = LocalDate.now();
    private Account account;

    @BeforeEach
    public void setup() {
        this.account = new Account();
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
    public void testWithdraw_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> this.account.withdraw(0, date)
        );
        assertEquals("Amount not be less than 1", exception.getMessage());
    }

    @Test
    public void testWithdraw_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> this.account.withdraw(-1, date)
        );
        assertEquals("Amount not be less than 1", exception.getMessage());
    }

    @Test
    public void testWithdraw_InvalidFunds() {
        this.account.deposit(1, date);
        var exception = assertThrows(
                RuntimeException.class,
                () -> this.account.withdraw(2, date)
        );
        assertEquals("Invalid funds to make transaction", exception.getMessage());
    }

    @Test
    public void testDeposit_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> this.account.deposit(0, date)
        );
        assertEquals("Amount not be less than 1", exception.getMessage());
    }

    @Test
    public void testDeposit_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> this.account.deposit(-1, date)
        );
        assertEquals("Amount not be less than 1", exception.getMessage());
    }

    @Test
    public void testFilterTransactionByAmount() {
        this.account.deposit(100, date);
        this.account.deposit(200, date);
        AmountFilter amountFilter = new AmountFilter(100);
        assertEquals(
                new Transaction(TransactionType.DEPOSIT, 100, date),
                this.account.filter(amountFilter).getFirst());
    }

    @Test
    public void testFilterTransactionByDate() {
        LocalDate datePlus100Days = date.plusDays(100);
        this.account.deposit(100, date);
        this.account.deposit(200, datePlus100Days);
        DateFilter dateFilter = new DateFilter(datePlus100Days);
        assertEquals(
                new Transaction(TransactionType.DEPOSIT, 200, datePlus100Days),
                this.account.filter(dateFilter).getFirst());
    }

    @Test
    public void testFilterTransactionByWithdrawalType() {
        this.account.deposit(300, date);
        this.account.withdraw(200, date);
        TypeFilter typeFilter = new TypeFilter(TransactionType.WITHDRAWAL);
        assertEquals(
                new Transaction(TransactionType.WITHDRAWAL, 200, date),
                this.account.filter(typeFilter).getFirst());
    }

    @Test
    public void testFilterTransactionByDepositType() {
        this.account.deposit(300, date);
        this.account.withdraw(200, date);
        TypeFilter typeFilter = new TypeFilter(TransactionType.DEPOSIT);
        assertEquals(
                new Transaction(TransactionType.DEPOSIT, 300, date),
                this.account.filter(typeFilter).getFirst());
    }

}
