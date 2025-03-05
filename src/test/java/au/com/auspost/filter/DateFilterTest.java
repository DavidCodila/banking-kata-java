package au.com.auspost.filter;

import au.com.auspost.transaction.Transaction;
import au.com.auspost.transaction.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateFilterTest {
    private final LocalDate date = LocalDate.now();
    private final LocalDate datePlus100Days = this.date.plusDays(100);
    private final DateFilter dateFilter = new DateFilter(date);

    @Test
    public void testAmountDatePresent() {
        assertTrue(this.dateFilter.apply(new Transaction(TransactionType.DEPOSIT, 1, this.date)));
    }

    @Test
    public void testAmountDateNotPresent() {
        assertFalse(this.dateFilter.apply(new Transaction(TransactionType.DEPOSIT, 1, this.datePlus100Days)));
    }
}
