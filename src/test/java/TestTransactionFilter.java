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
    private List<Transaction> transactions;
    public TransactionFilter filter;

    @BeforeEach
    public void setup() {
        transactions = new ArrayList<>();
        this.filter = new TransactionFilter();
        this.transactions.add(new Transaction(100, 100, TransactionType.DEPOSIT, date));
        this.transactions.add(new Transaction(500, 600, TransactionType.DEPOSIT, date));
        this.transactions.add(new Transaction(100, 500, TransactionType.WITHDRAWAL, datePlus100Days));
        this.transactions.add(new Transaction(200, 300, TransactionType.WITHDRAWAL, datePlus200Days));
        this.filter.loadTransactions(this.transactions);
    }

    @Test
    public void testGetAllTransactions() {
        String expectedOutput =
                  date + "\t+100\t100" + "\n"
                + date + "\t+500\t600" + "\n"
                + datePlus100Days + "\t-100\t500" + "\n"
                + datePlus200Days + "\t-200\t300";
        assertEquals(expectedOutput, this.filter.getAllTransactions());
    }

    @Test
    public void testGetTransactionsByAmount() {
        String expectedOutput =
                  date + "\t+100\t100" + "\n"
                + datePlus100Days + "\t-100\t500";
        assertEquals(expectedOutput, this.filter.getTransactionsByAmount(100));
    }

    @Test
    public void testGetTransactionsByTypeDeposit() {
        List<Transaction> expectedOutput = this.transactions.subList(0, 2);
        assertEquals(expectedOutput, TransactionType.getDepositTransactions(transactions));
    }

    @Test
    public void testGetTransactionsByTypeWithdrawal() {
        List<Transaction> expectedOutput = this.transactions.subList(2, 4);
        assertEquals(expectedOutput, TransactionType.getWithdrawalTransactions(transactions));
    }

    @Test
    public void testGetTransactionsByDate() {
        String expectedOutput =
                  date + "\t+100\t100" + "\n"
                + date + "\t+500\t600";
        assertEquals(expectedOutput, this.filter.getTransactionsByDate(date));
    }

    @Test
    public void testGetTransactionsByDateWithNoTransactions() {
        String expectedOutput = "";
        assertEquals(expectedOutput, this.filter.getTransactionsByDate(date.plusDays(300)));
    }
}
