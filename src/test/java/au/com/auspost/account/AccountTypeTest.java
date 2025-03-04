package au.com.auspost.account;

import au.com.auspost.transaction.Transaction;
import au.com.auspost.transaction.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTypeTest {
    private static final LocalDate date = LocalDate.now();

    @Test
    public void testGetFormattedDepositTransaction() {
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, 100, date);
        String expectedOutput = date + "\t+100";
        assertEquals(expectedOutput, transaction.type().getFormattedTransaction(transaction));
    }

    @Test
    public void testGetFormattedWithdrawTransaction() {
        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, 100, date);
        String expectedOutput = date + "\t-100";
        assertEquals(expectedOutput, transaction.type().getFormattedTransaction(transaction));
    }
}
