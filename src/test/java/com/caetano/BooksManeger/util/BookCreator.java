package com.caetano.BooksManeger.util;

import com.caetano.BooksManeger.domain.Book;

public class BookCreator {

    public static Book bookCreaterToBeSaved() {
        return Book.builder()
                .nameBook("The Walking Dead")
                .build();

    }

    public static Book bookCreaterValid() {
        return Book.builder()
                .nameBook("The Walking Dead")
                .id(1L)
                .build();

    }

    public   static Book bookCreaterUpdate() {
        return Book.builder()
                .nameBook("The sun in the Mountain")
                .id(1L)
                .build();

    }
}
