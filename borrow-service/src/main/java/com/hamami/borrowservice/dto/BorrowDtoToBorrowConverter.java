package com.hamami.borrowservice.dto;

import com.hamami.borrowservice.model.Borrow;

import java.time.LocalDate;

public class BorrowDtoToBorrowConverter {
    public static Borrow toEntity(BorrowDto borrowDto){
        Borrow borrow = new Borrow();
        borrow.setId(borrowDto.getId());
        borrow.setBorrowed(borrowDto.isBorrowed());
        borrow.setDueDate(borrowDto.getDueDate());
        borrow.setTitle(borrowDto.getTitle());
        return borrow;
    }
}
