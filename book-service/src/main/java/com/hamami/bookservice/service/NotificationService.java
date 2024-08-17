package com.hamami.bookservice.service;

import com.hamami.bookservice.model.Book;
import com.hamami.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Scheduled(cron = "0 0 0 * * *")  // Runs daily at midnight
    public void checkForDueBooks() {
        LocalDate fiveDaysFromNow = LocalDate.now().plusDays(5);

        List<Book> dueBooks = bookRepository.findByDueDateAndIsBorrowed(fiveDaysFromNow, true);

        dueBooks.forEach(book -> {
            log.info("Book '{}' is due in 5 days on {}", book.getTitle(), book.getDueDate());
        });
    }
}
