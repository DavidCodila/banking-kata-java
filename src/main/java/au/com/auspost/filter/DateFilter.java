package au.com.auspost.filter;

import au.com.auspost.transaction.Transaction;

import java.time.LocalDate;


public class DateFilter implements Filter {
    private final LocalDate date;

    public DateFilter(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean apply(Transaction transaction) {
        return this.date == transaction.date();
    }
}
