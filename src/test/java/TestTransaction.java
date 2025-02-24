import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransaction {
    private static final LocalDate date = LocalDate.now();

    @Test
    public void testTransaction_Withdraw2() {
        Transaction transaction = new Transaction(2, 1, TransactionType.WITHDRAWAL.symbol, date);
        assertEquals(date + "\t-2\t1", transaction.getInformation());
    }

    @Test
    public void testTransaction_Deposit2() {
        Transaction transaction = new Transaction(2, 2, TransactionType.DEPOSIT.symbol, date);
        assertEquals(date + "\t+2\t2", transaction.getInformation());
    }

}
