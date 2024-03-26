package com.caetano.BooksManeger.repository;

import com.caetano.BooksManeger.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long>{

     List<Book> findByNameBook(String nameBook);
}
