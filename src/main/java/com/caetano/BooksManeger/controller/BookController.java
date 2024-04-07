package com.caetano.BooksManeger.controller;


import com.caetano.BooksManeger.domain.Book;
import com.caetano.BooksManeger.request.BookPostRequestBody;
import com.caetano.BooksManeger.request.BookPutRequestBody;
import com.caetano.BooksManeger.service.ServiceBook;
import com.caetano.BooksManeger.util.DataUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Log4j2
public class BookController {


    private final ServiceBook serviceBook;


    @GetMapping
    public Page<Book> list(Pageable pageable) {
        return serviceBook.listAll(pageable);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Book>> listAllNonPageable( ) {
        return ResponseEntity.ok(serviceBook.listAllNonPageable());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable long id){
        return ResponseEntity.ok(serviceBook.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Book>> findByNameBook(@RequestParam(name = "nameBook")String nameBook){
        return ResponseEntity.ok(serviceBook.findByNameBook(nameBook));
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody @Valid BookPostRequestBody bookPostRequestBody){
        return new ResponseEntity<>(serviceBook.save(bookPostRequestBody), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remove (@PathVariable Long id){
        serviceBook.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping
    public ResponseEntity<Void>replace (@RequestBody BookPutRequestBody bookPutRequestBody){
        serviceBook.replace(bookPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
