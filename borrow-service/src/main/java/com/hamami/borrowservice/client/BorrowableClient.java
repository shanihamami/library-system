package com.hamami.borrowservice.client;

import java.time.LocalDate;

public interface BorrowableClient {
    boolean isItemBorrowed(Long id);
    void updateItemAsBorrowed(Long id, boolean isBorrowed, LocalDate dueDate);
}