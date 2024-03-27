package com.caetano.BooksManeger.repository;

import com.caetano.BooksManeger.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Test for Book Repository")
@Log4j2
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Save persist book when Successful")
    void save_PersistBook_WhenSuccessful() {

        Book bookToBeSaved = bookCreater();

        Book booksaved = this.bookRepository.save(bookToBeSaved);

        org.assertj.core.api.Assertions.assertThat(booksaved).isNotNull();

        org.assertj.core.api.Assertions.assertThat(booksaved.getId()).isNotNull();

        org.assertj.core.api.Assertions.assertThat(booksaved.getNameBook()).isEqualTo(bookToBeSaved.getNameBook());


    }

    @Test
    @DisplayName("Save update book when Successful")
    void save_UpdateBook_WhenSuccessful() {

        Book bookToBeSaved = bookCreater();

        Book booksaved = this.bookRepository.save(bookToBeSaved);

        booksaved.setNameBook("Fault of the stars");

        Book bookUpdate = this.bookRepository.save(booksaved);

        org.assertj.core.api.Assertions.assertThat(bookUpdate).isNotNull();

        org.assertj.core.api.Assertions.assertThat(bookUpdate.getId()).isNotNull();

        org.assertj.core.api.Assertions.assertThat(bookUpdate.getNameBook()).isEqualTo(booksaved.getNameBook());


    }

    @Test
    @DisplayName("Delete remove book when Successful")
    void delete_RemoveBook_WhenSuccessful() {

        Book bookToBeSaved = bookCreater();

        Book booksaved = this.bookRepository.save(bookToBeSaved);

         this.bookRepository.delete(booksaved);

         Optional<Book> bookOptional = this.bookRepository.findById(booksaved.getId());

        Assertions.assertThat(bookOptional).isEmpty();


    }

    @Test
    @DisplayName("Find By list of the books when Successful")
    void findByName_ReturnListOfTheBooks_WhenSuccessful() {

        Book bookToBeSaved = bookCreater();

        Book booksaved = this.bookRepository.save(bookToBeSaved);

         String name = booksaved.getNameBook();

        List<Book> books = this.bookRepository.findByNameBook(name);

        Assertions.assertThat(books).isNotEmpty();

        Assertions.assertThat(books).contains(booksaved);


    }

    @Test
    @DisplayName("Find By Name returns empty when no book is not found")
    void findByName_ReturnEmptyList_WhenBookIsNotFound() {

        List<Book> books = this.bookRepository.findByNameBook("House Monster");

        Assertions.assertThat(books).isEmpty();



    }


    private Book bookCreater() {
        return Book.builder()
                .nameBook("The Walking Dead")
                .build();

    }
}