package com.hamami.borrowservice.controller;

import com.hamami.borrowservice.dto.BorrowDto;
import com.hamami.borrowservice.client.BooksClient;
import com.hamami.borrowservice.service.BaseBorrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for managing borrowing operations.
 * This controller handles HTTP requests related to borrowing books and querying available books.
 */
@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
@Slf4j
public class BorrowController {
    private final BaseBorrowService borrowService;
    private final BooksClient booksClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void borrow(@RequestBody BorrowDto borrowDto) {
        borrowService.borrow(borrowDto);
        log.info("Book borrowed successfully");
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> getAvailableBooks() {
        return booksClient.getAvailableBooks();

    }
}
