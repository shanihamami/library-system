package com.hamami.bookservice.service;

import com.hamami.bookservice.dto.BookDto;
import com.hamami.bookservice.dto.BookDtoToBookConverter;
import com.hamami.bookservice.model.Book;
import com.hamami.bookservice.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Adds a new book to the library.
     * This method is transactional, ensuring that changes are rolled back if an exception occurs during the process.
     *
     * @param bookDto the data transfer object containing the book's details
     * @return the newly added book with an assigned ID, reflecting the persisted state
     */
    @Transactional
    public Book addBook(BookDto bookDto) {
        if (bookDto == null) {
            throw new IllegalArgumentException("Book DTO must not be null");
        }
        try {
            Book book = BookDtoToBookConverter.toEntity(bookDto);
            bookRepository.save(book);
            log.info("Book added successfully: {}", book);
            return book;
        } catch (Exception e) {
            throw new ServiceException("Failed to add book", e);
        }
    }

    /**
     * Retrieves a list of all books in the library.
     *
     * @return a list of books currently stored in the library
     */
    public List<Book> getAllBooks() {
        try {
            log.debug("Retrieving all books from the database");
            List<Book> books = bookRepository.findAll();
            books.forEach(b -> {
                log.info(b.toString());
            });
            log.info("Retrieved {} books", books.size());
            return books;
        } catch (Exception e) {
            log.error("Failed to retrieve books", e);
            throw e;
        }
    }
    /**
     * Checks if a specific book is currently borrowed. This method is used by the Borrow service client
     * to verify the borrowing status of a book.
     *
     * @param id the unique identifier of the book
     * @return true if the book is borrowed, false otherwise
     */
    public boolean isBorrowed(Long id) {
        try {
            // Retrieve the book by its ID
            Optional<Book> book = bookRepository.findById(id);

            // Check if the book exists and return its isBorrowed status
            if (book.isPresent()) {
                boolean result = book.get().isBorrowed();
                log.debug("Checked borrow status for book id {}: {}", id, result);
                return result;
            } else {
                log.error("Book not found for id: {}", id);
                throw new ServiceException("Book not found");
            }
        } catch (Exception e) {
            log.error("Error checking if book is borrowed for id {}: {}", id, e.getMessage());
            throw new ServiceException("Unable to check if book is borrowed", e);
        }
    }

    /**
     * Updates the borrowing status of a book in the library. This method is used by the Borrow service client
     * to update the status of a book when it is borrowed. It includes changing the availability status
     * and setting the due date.
     *
     * @param id the unique identifier of the book
     * @param isBorrowed the new borrowing status to set
     * @param dueDate the return due date for the book
     */
    public void updateBookAsBorrowed(Long id, boolean isBorrowed, LocalDate dueDate) {
        // Retrieve the book from the repository
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

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
