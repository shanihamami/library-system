package com.hamami.bookservice.dto;

import com.hamami.bookservice.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * Data Transfer Object for Book entity.
 * This class is used to transfer book data across different layers of the application,
 */
@Setter
@Getter
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private Genre genre;
    private boolean isBorrowed;
    private LocalDate dueDate;

    /**
     * Checks if the book is currently borrowed.
     * @return boolean indicating if the book is borrowed.
     */
    public boolean isBorrowed() {
        return isBorrowed;
    }

}
