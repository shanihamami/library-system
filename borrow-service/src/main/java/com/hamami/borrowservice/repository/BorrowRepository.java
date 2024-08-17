package com.hamami.borrowservice.repository;

import com.hamami.borrowservice.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
