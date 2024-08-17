package com.hamami.borrowservice.client;

import com.hamami.borrowservice.dto.BorrowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@FeignClient(value = "book-service", url="http://localhost:8080")
public interface BooksClient {

    @GetMapping("/api/books/{id}/isBorrowed")
    boolean isBookBorrowed(@PathVariable Long id);

    @PostMapping("/api/books/{id}/borrow")
    void updateBookAsBorrowed(@PathVariable Long id, @RequestParam boolean isBorrowed, @RequestParam LocalDate dueDate);

    @GetMapping("/api/books/available")
    List<Map<String, Object>> getAvailableBooks();

//    public void updateBorrowed

}
