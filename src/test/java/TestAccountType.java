import Transaction.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountType {
    private static final LocalDate date = LocalDate.now();

    @Test
    public void testGetFormattedDepositTransaction() {
        Transaction transaction = new Transaction(new Amount(TransactionType.DEPOSIT, 100), date);
        String expectedOutput = date + "\t+100\t100" + "\n";
        assertEquals(expectedOutput, transaction.amount().type().getFormattedTransaction(transaction));
    }

    @Test
    public void testGetFormattedWithdrawTransaction() {
        Transaction transaction = new Transaction(new Amount(TransactionType.WITHDRAWAL, 100), date);
        String expectedOutput = date + "\t-100\t100" + "\n";
        assertEquals(expectedOutput, transaction.amount().type().getFormattedTransaction(transaction));
    }
}
