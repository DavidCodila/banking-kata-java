import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransactionFilter {
    private static final LocalDate date = LocalDate.now();
    private static final LocalDate datePlus100Days = date.plusDays(100);
    private static final LocalDate datePlus200Days = date.plusDays(200);
    private static List<Transaction> transactions;

    @BeforeEach
    public void setup() {
        transactions = new ArrayList<>();
        transactions.add(new Transaction(100, 100, TransactionType.DEPOSIT, date));
        transactions.add(new Transaction(500, 600, TransactionType.DEPOSIT, date));
        transactions.add(new Transaction(100, 500, TransactionType.WITHDRAWAL, datePlus100Days));
        transactions.add(new Transaction(200, 300, TransactionType.WITHDRAWAL, datePlus200Days));
    }

    @Test
    public void testGetAllTransactions() {
        String expectedOutput =
                  date + "\t+100\t100" + "\n"
                + date + "\t+500\t600" + "\n"
                + datePlus100Days + "\t-100\t500" + "\n"
                + datePlus200Days + "\t-200\t300";
        assertEquals(expectedOutput, TransactionFilter.getAllTransactions(transactions));
    }

    @Test
    public void testGetTransactionsByAmount() {
        String expectedOutput =
                  date + "\t+100\t100" + "\n"
                + datePlus100Days + "\t-100\t500";
        assertEquals(expectedOutput, TransactionFilter.getTransactionsByAmount(transactions,100));
    }

    @Test
    public void testGetTransactionsByTypeDeposit() {
        List<Transaction> expectedOutput = transactions.subList(0, 2);
        assertEquals(expectedOutput, TransactionType.getDepositTransactions(transactions));
    }

    @Test
    public void testGetTransactionsByTypeWithdrawal() {
        List<Transaction> expectedOutput = transactions.subList(2, 4);
        assertEquals(expectedOutput, TransactionType.getWithdrawalTransactions(transactions));
    }

    @Test
    public void testGetTransactionsByDate() {
        String expectedOutput =
                  date + "\t+100\t100" + "\n"
                + date + "\t+500\t600";
        assertEquals(expectedOutput, TransactionFilter.getTransactionsByDate(transactions, date));
    }

    @Test
    public void testGetTransactionsByDateWithNoTransactions() {
        String expectedOutput = "";
        assertEquals(expectedOutput, TransactionFilter.getTransactionsByDate(transactions, date.plusDays(300)));
    }
}
