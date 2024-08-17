package com.hamami.bookservice.dto;

import com.hamami.bookservice.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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


    public boolean isBorrowed() {
        return isBorrowed;
    }

}
