package com.hamami.bookservice.service;

import com.hamami.bookservice.dto.BookDto;
import com.hamami.bookservice.dto.BookDtoToBookConverter;
import com.hamami.bookservice.exceptions.BookNotFoundException;
import com.hamami.bookservice.exceptions.BookPersistenceException;
import com.hamami.bookservice.exceptions.BookRetrievalException;
import com.hamami.bookservice.model.Book;
import com.hamami.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Service class for handling book operations.
 * This includes adding books, retrieving books, and updating book statuses.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Adds a new book to the library.
     * This method is transactional, ensuring that changes are rolled back if an exception occurs during the process.
     * @param bookDto the data transfer object containing the book's details
     * @return the newly added book with an assigned ID, reflecting the persisted state
     */
    public Book addBook(BookDto bookDto) throws BookPersistenceException {
        if (bookDto == null) {
            throw new IllegalArgumentException("Book DTO must not be null");
        }
        try {
            Book book = BookDtoToBookConverter.toEntity(bookDto);
            book = bookRepository.save(book);  // Save the book and capture the returned persisted entity
            log.info("Book added successfully: {}", book);
            return book;
        } catch (DataAccessException ex) {  // Catching a generic data access exception from Spring
            log.error("Error adding book to the database: {}", ex.getMessage(), ex);
            throw new BookPersistenceException("Failed to add book to the database", ex);
        }
    }

    /**
     * Retrieves a list of all books in the library.
     * @return a list of books currently stored in the library
     */
    public List<Book> getAllBooks() {
        try {
            log.debug("Retrieving all books from the database");
            List<Book> books = bookRepository.findAll();
            books.forEach(b -> log.info(b.toString()));
            log.info("Retrieved {} books", books.size());
            return books;
        } catch (Exception e) {
            log.error("Failed to retrieve books", e);
            throw new BookRetrievalException("Unable to retrieve books from the database", e);
        }
    }
    /**
     * Checks if a book is currently borrowed.
     * @param id the unique identifier of the book
     * @return true if the book is borrowed, false otherwise
     */
    public boolean isBorrowed(Long id) {
        // Attempt to find the book and check its borrowed status
        return bookRepository.findById(id)
                .map(Book::isBorrowed)
                .orElseThrow(() -> new BookNotFoundException("Book not found for id: " + id));
    }

    /**
     * Updates the borrowing status of a book and setting the due date.
     * @param id the unique identifier of the book
     * @param isBorrowed the new borrowing status to set
     * @param dueDate the return due date for the book
     */
    public void updateBookAsBorrowed(Long id, boolean isBorrowed, LocalDate dueDate) {
        // Retrieve the book from the repository
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found for id: " + id));

        // Update the book's isBorrowed status and dueDate
        book.setBorrowed(isBorrowed);
        book.setDueDate(dueDate);

        // Save the updated book back to the repository
        bookRepository.save(book);
    }

    public List<Map<String, Object>> getAvailableBooks() {
        List<Book> availableBooks = bookRepository.findByIsBorrowedFalse();

        // Convert to a list of maps
        return availableBooks.stream()
                .map(book -> {
                    Map<String, Object> bookDetails = new HashMap<>();
                    bookDetails.put("id", book.getId());
                    bookDetails.put("title", book.getTitle());
                    bookDetails.put("author", book.getAuthor());
                    return bookDetails;
                })
                .collect(Collectors.toList());
    }
}
