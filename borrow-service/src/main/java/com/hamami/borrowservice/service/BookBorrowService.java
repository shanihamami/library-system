package com.hamami.borrowservice.service;

import com.hamami.borrowservice.client.BooksClient;
import com.hamami.borrowservice.dto.BorrowDto;
import com.hamami.borrowservice.repository.BorrowRepository;
import exceptions.NotAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


/**
 * Service class for handling borrowing operations.
 * This includes borrowing books, retrieving available books
 */
@Service
@RequiredArgsConstructor
public class BookBorrowService implements BaseBorrowService {

    private final BorrowRepository borrowRepository;
    private final BooksClient booksClient;

    /**
     * Attempts to borrow a book based on the provided BorrowDto.
     * Checks if the book is currently borrowed and updates its status if it is available.
     * @param borrowDto Data Transfer Object containing the details necessary for borrowing a book.
     * @throws NotAvailableException if the book is currently borrowed and thus not available for new borrows.
     */
    public void borrow(BorrowDto borrowDto) {
        boolean isBorrowed = booksClient.isItemBorrowed(borrowDto.getId());
        if (!isBorrowed) {
            LocalDate dueDate = LocalDate.now().plusMonths(1);
            booksClient.updateItemAsBorrowed(borrowDto.getId(), true, dueDate);
        } else {
            throw new NotAvailableException("The book: " + borrowDto.getTitle() + " is not available.");
        }
    }
}
