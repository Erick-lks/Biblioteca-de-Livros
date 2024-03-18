package com.caetano.demo.repository;

import com.caetano.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long>{

     List<Book> findByNameBook(String nameBook);
}
