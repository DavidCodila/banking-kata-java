package au.com.auspost.filter;

import au.com.auspost.transaction.Transaction;

public interface Filter {
    boolean apply(Transaction transaction);
}
