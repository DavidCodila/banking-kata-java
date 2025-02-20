import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.deposit(0)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }

    @Test
    public void testAccountDeposit_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> account.deposit(-1)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }
}
