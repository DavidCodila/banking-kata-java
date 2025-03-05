package au.com.auspost.filter;

import au.com.auspost.transaction.Transaction;
import au.com.auspost.transaction.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmountFilterTest {
    private final LocalDate date = LocalDate.now();
    private final AmountFilter amountFilter = new AmountFilter(1);

    @Test
    public void testAmount1Present() {
        assertTrue(this.amountFilter.apply(new Transaction(TransactionType.DEPOSIT, 1, this.date)));
    }

    @Test
    public void testAmount1NotPresent() {
        assertFalse(this.amountFilter.apply(new Transaction(TransactionType.DEPOSIT, 2, this.date)));
    }
}
