package com.hamami.bookservice.repository;

import com.hamami.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Query to find all books that are due on a specific date and are currently borrowed
    List<Book> findByDueDateAndIsBorrowed(LocalDate dueDate, boolean isBorrowed);

    // Query to find all available books (not borrowed)
    List<Book> findByIsBorrowedFalse();
}
