package com.hamami.bookservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


/**
 * Entity model for Book.
 * Represents a book in the library system.
 */
@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private boolean isBorrowed;
    private LocalDate dueDate;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", isBorrowed=" + isBorrowed +
                ", dueDate=" + dueDate +
                '}';
    }
}




