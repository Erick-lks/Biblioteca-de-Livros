package com.caetano.BooksManeger.repository;

import com.caetano.BooksManeger.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DisplayName("Test for Book Repository")
class BookRepositoryTest {
 @Autowired
    private BookRepository bookRepository;
    @Test
    @DisplayName("Save creates book when Successful")
    void save_PersistBook_WhenSuccessful(){
        Book bookToBeSaved = bookCreater();
        Book booksaved = this.bookRepository.save(bookToBeSaved);
        org.assertj.core.api.Assertions.assertThat(booksaved).isNotNull();
        org.assertj.core.api.Assertions.assertThat(booksaved.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(booksaved.getNameBook()).isEqualTo(bookToBeSaved.getNameBook());


    }

 private Book bookCreater(){
        return Book.builder()
                .nameBook("The Walking Dead")
                .build();

 }
}