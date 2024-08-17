package com.hamami.borrowservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
