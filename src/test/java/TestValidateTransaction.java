import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidateTransaction {

    @Test
    public void testWithdraw_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> ValidateTransaction.validateWithdrawal(0, 100)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testWithdraw_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> ValidateTransaction.validateWithdrawal(-1, 100)
        );
        assertEquals(exception.getMessage(), "Can not withdraw less than 1");
    }

    @Test
    public void testWithdraw_InvalidFunds() {
        var exception = assertThrows(
                RuntimeException.class,
                () -> ValidateTransaction.validateWithdrawal(2, 1)
        );
        assertEquals(exception.getMessage(), "Invalid funds to make transaction");
    }

    @Test
    public void testDeposit_0() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> ValidateTransaction.validateDeposit(0)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }

    @Test
    public void testDeposit_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> ValidateTransaction.validateDeposit(-1)
        );
        assertEquals(exception.getMessage(), "Can not deposit less than 1");
    }

}
