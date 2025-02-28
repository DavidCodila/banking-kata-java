import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountType {
    private static final LocalDate date = LocalDate.now();

    @Test
    public void testGetFormattedDepositTransaction() {
        Transaction transaction = new Transaction(100, 100, TransactionType.DEPOSIT, date);
        String expectedOutput = date + "\t+100\t100" + "\n";
        assertEquals(expectedOutput, transaction.type().getFormattedTransaction(transaction));
    }

    @Test
    public void testGetFormattedWithdrawTransaction() {
        Transaction transaction = new Transaction(100, 100, TransactionType.WITHDRAWAL, date);
        String expectedOutput = date + "\t-100\t100" + "\n";
        assertEquals(expectedOutput, transaction.type().getFormattedTransaction(transaction));
    }
}
