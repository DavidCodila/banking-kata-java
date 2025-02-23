import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransaction {
    private static final LocalDate date = LocalDate.now();

    @Test
    public void testTransaction_Deposit0_Withdraw0() {
        Transaction transaction = new Transaction(2, 1, Constants.withdrawalType, date);
        assertEquals(date + "\t-2\t1", transaction.getInformation());
    }

}
