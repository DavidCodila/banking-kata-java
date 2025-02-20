import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccount {
    private Account account;

    @BeforeEach
    public void setup() {
        account = new Account();
    }

    @Test
    public void testAccountDeposit_1() {
        account.deposit(1);
        assertEquals(1, account.getBalance());
    }

    @Test
    public void testAccountDeposit_0() {
        account.deposit(0);
        assertEquals(0, account.getBalance());
    }
}
