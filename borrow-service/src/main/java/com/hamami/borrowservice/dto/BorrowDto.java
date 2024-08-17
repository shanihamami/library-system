package com.hamami.borrowservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * Data Transfer Object for borrow operations.
 * Contains details necessary for managing a book borrowing transaction.
 */
@Setter
@Getter
@AllArgsConstructor
public class BorrowDto {

    private Long id;
    private String title;
    private boolean isBorrowed;
    private LocalDate dueDate;

    public BorrowDto() {

    }

}
