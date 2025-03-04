package Filter;

import Transaction.*;


public class TypeFilter implements Filter {
    private final TransactionType type;

    public TypeFilter(TransactionType type) {
        this.type = type;
    }

    @Override
    public boolean apply(Transaction transaction) {
        return this.type == transaction.amount().type();
    }
}
