package com.hamami.bookservice.dto;

import com.hamami.bookservice.model.Book;

/**
 * Converter class to transform BookDto into a Book entity.
 */
public class BookDtoToBookConverter {

    /**
     * Converts a BookDto object to a Book entity.
     * @param bookDto the BookDto object to be converted.
     * @return Book the converted Book entity with values mapped from the BookDto.
     */
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
