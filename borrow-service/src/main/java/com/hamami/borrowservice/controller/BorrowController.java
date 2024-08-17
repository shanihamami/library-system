package com.hamami.borrowservice.controller;

import com.hamami.borrowservice.dto.BorrowDto;
import com.hamami.borrowservice.client.BooksClient;
import com.hamami.borrowservice.service.BorrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
@Slf4j
public class BorrowController {
    private final BorrowService borrowService;
    private final BooksClient booksClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void borrow(@RequestBody BorrowDto borrowDto) {
        borrowService.borrowBook(borrowDto);
        log.info("Book borrowed successfully");
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> getAvailableBooks() {
        return booksClient.getAvailableBooks();

    }
}
