package com.hamami.borrowservice.service;

import com.hamami.borrowservice.dto.BorrowDto;
import exceptions.NotAvailableException;

public interface BaseBorrowService {
    void borrow(BorrowDto borrowDto) throws NotAvailableException;
}