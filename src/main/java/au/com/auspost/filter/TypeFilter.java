package au.com.auspost.filter;

import au.com.auspost.transaction.Transaction;
import au.com.auspost.transaction.TransactionType;


public class TypeFilter implements Filter {
    private final TransactionType type;

    public TypeFilter(TransactionType type) {
        this.type = type;
    }

    @Override
    public boolean apply(Transaction transaction) {
        return this.type == transaction.type();
    }
}
