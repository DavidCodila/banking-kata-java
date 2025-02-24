import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransactionFilter {
    private static final LocalDate date = LocalDate.now();
    private static List<Transaction> transactions;

    @BeforeEach
    public void setup() {
        transactions = new ArrayList<>();
        transactions.add(new Transaction(100, 100, TransactionType.DEPOSIT.symbol, date));
        transactions.add(new Transaction(500, 600, TransactionType.DEPOSIT.symbol, date));
        transactions.add(new Transaction(100, 500, TransactionType.WITHDRAWAL.symbol, date));
        transactions.add(new Transaction(200, 300, TransactionType.WITHDRAWAL.symbol, date));
    }

    @Test
    public void testGetAllTransactions() {
        String expectedOutput =
                  date + "\t+100\t100" + "\n"
                + date + "\t+500\t600" + "\n"
                + date + "\t-100\t500" + "\n"
                + date + "\t-200\t300";
        assertEquals(expectedOutput, TransactionFilter.getAllTransactions(transactions));
    }

    @Test
    public void testGetTransactionsByAmount() {
        String expectedOutput =
                  date + "\t+100\t100" + "\n"
                + date + "\t-100\t500";
        assertEquals(expectedOutput, TransactionFilter.getTransactionsByAmount(transactions,100));
    }
}
