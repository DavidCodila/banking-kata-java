import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccount {
    @Test
    public void testAccountDeposit_1() {
        Account account = new Account();
        account.deposit(1);
        assertEquals(1, account.getBalance());
    }
}
