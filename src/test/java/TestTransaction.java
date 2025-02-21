import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransaction {
    private static String date = "1.1.2025";

    @Test
    public void testTransaction_Deposit0_Withdraw0() {
        Transaction transaction = new Transaction("+2", 1, date);
        assertEquals(date + "\t+2\t1", transaction.getInformation());
    }
    
}
