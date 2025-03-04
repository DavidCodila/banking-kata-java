package Filter;

import Transaction.Transaction;

public class AmountFilter implements Filter {
    private final int amount;

    public AmountFilter(int amount) {
        this.amount = amount;
    }


    @Override
    public boolean apply(Transaction transaction) {
        return this.amount == transaction.amount().amount();

    }
}
