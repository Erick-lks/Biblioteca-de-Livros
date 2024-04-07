package com.caetano.BooksManeger.controller;

import com.caetano.BooksManeger.domain.Book;
import com.caetano.BooksManeger.service.ServiceBook;
import com.caetano.BooksManeger.util.BookCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class BookControllerTest {
    @InjectMocks

    private BookController bookController;

    @Mock
    private ServiceBook serviceBookMock;

    @BeforeEach
    void setUp(){
         PageImpl<Book> bookPage = new PageImpl<>(List.of(BookCreator.bookCreaterValid()));
        BDDMockito.when(serviceBookMock.listAll(ArgumentMatchers.any()))
                .thenReturn(bookPage);

    }
    @Test
    @DisplayName("list returns list of book inside page object when successful")
    void list_ReturnsListOfBooksInsidesPageObject_WhenSuccessful(){
         String expectedName = BookCreator.bookCreaterValid().getNameBook();
         Page<Book> bookPage = bookController.list(null);

        Assertions.assertThat(bookPage).isNotNull();
        Assertions.assertThat(bookPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(bookPage.toList().get(0).getNameBook()).isEqualTo(expectedName);

    }

}