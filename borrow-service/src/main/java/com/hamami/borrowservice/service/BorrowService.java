package com.hamami.borrowservice.service;

import com.hamami.borrowservice.client.BooksClient;
import com.hamami.borrowservice.dto.BorrowDto;
import com.hamami.borrowservice.dto.BorrowDtoToBorrowConverter;
import com.hamami.borrowservice.model.Borrow;
import com.hamami.borrowservice.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BooksClient booksClient;

    public void borrowBook(BorrowDto borrowDto) {
        boolean isBookAvailable = !booksClient.isBookBorrowed(borrowDto.getId());
        if (isBookAvailable) {
            LocalDate dueDate = LocalDate.now().plusMonths(1);
            booksClient.updateBookAsBorrowed(borrowDto.getId(), true, dueDate);
        } else {
            throw new RuntimeException("The book: " + borrowDto.getTitle() + " is not available.");
        }
    }
}
