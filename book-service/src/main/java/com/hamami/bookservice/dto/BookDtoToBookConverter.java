package com.hamami.bookservice.dto;

import com.hamami.bookservice.model.Book;

public class BookDtoToBookConverter {
    public static Book toEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setBorrowed(bookDto.isBorrowed());
        book.setDueDate(bookDto.getDueDate());
        return book;
    }
}
