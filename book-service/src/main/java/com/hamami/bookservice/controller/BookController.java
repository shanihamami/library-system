package com.hamami.bookservice.controller;

import com.hamami.bookservice.dto.BookDto;
import com.hamami.bookservice.model.Book;
import com.hamami.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @Operation(hidden = true)
    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @Operation(hidden = true)
    @GetMapping("/{id}/isBorrowed")
    public boolean isBorrowed(@PathVariable Long id) {
        return bookService.isBorrowed(id);
    }
    @Operation(hidden = true)
    @PostMapping("/{id}/borrow")
    public ResponseEntity<?> updateBookAsBorrowed(@PathVariable Long id, @RequestParam boolean isBorrowed, @RequestParam LocalDate dueDate) {
        try {
            bookService.updateBookAsBorrowed(id, isBorrowed, dueDate);
            return ResponseEntity.ok("Book status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update book status");
        }
    }


}
