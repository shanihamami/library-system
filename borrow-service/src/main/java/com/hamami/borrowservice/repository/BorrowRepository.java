package com.hamami.borrowservice.repository;

import com.hamami.borrowservice.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the Borrow entity.
 * Extends JpaRepository to provide basic CRUD operations on the Borrow table without the need for boilerplate code.
 * Note: Currently, this repository is not in use but is maintained for potential future use
 */
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
