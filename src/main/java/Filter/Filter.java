package Filter;

import Transaction.Transaction;

public interface Filter {
    boolean apply(Transaction transaction);
}
