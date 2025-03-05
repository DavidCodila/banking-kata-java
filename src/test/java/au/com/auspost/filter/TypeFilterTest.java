package au.com.auspost.filter;
import au.com.auspost.transaction.Transaction;
import au.com.auspost.transaction.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TypeFilterTest {
    private final LocalDate date = LocalDate.now();
    private final TypeFilter depostTypeFilter = new TypeFilter(TransactionType.DEPOSIT);
    private final TypeFilter withdrawalTypeFilter = new TypeFilter(TransactionType.WITHDRAWAL);

    @Test
    public void testDepositPresent() {
        assertTrue(this.depostTypeFilter.apply(new Transaction(TransactionType.DEPOSIT, 1, this.date)));
    }

    @Test
    public void testDepositNotPresent() {
        assertFalse(this.depostTypeFilter.apply(new Transaction(TransactionType.WITHDRAWAL, 1, this.date)));
    }

    @Test
    public void testWithdrawalPresent() {
        assertTrue(this.withdrawalTypeFilter.apply(new Transaction(TransactionType.WITHDRAWAL, 1, this.date)));
    }

    @Test
    public void testWithdrawalNotPresent() {
        assertFalse(this.withdrawalTypeFilter.apply(new Transaction(TransactionType.DEPOSIT, 1, this.date)));
    }
}
