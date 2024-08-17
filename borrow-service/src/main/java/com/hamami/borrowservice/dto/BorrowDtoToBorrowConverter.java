package com.hamami.borrowservice.dto;

import com.hamami.borrowservice.model.Borrow;

import java.time.LocalDate;

/**
 * Converter class to transform BorrowDto into a Borrow entity.
 */
public class BorrowDtoToBorrowConverter {

    /**
     * Converts a BorrowDto object to a Borrow entity.
     * @param borrowDto the BorrowDto object to be converted.
     * @return Borrow the converted Borrow entity with values mapped from the BorrowDto.
     */
    public static Borrow toEntity(BorrowDto borrowDto){
        Borrow borrow = new Borrow();
        borrow.setId(borrowDto.getId());
        borrow.setBorrowed(borrowDto.isBorrowed());
        borrow.setDueDate(borrowDto.getDueDate());
        borrow.setTitle(borrowDto.getTitle());
        return borrow;
    }
}
