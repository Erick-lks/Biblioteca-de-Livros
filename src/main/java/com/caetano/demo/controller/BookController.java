package com.caetano.demo.controller;


import com.caetano.demo.domain.Book;
import com.caetano.demo.request.BookPostRequestBody;
import com.caetano.demo.request.BookPutRequestBody;
import com.caetano.demo.service.ServiceBook;
import com.caetano.demo.util.DataUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final DataUtil dataUtil;
    private final ServiceBook serviceBook;

    //localhost:8080/book/list
    @GetMapping
    public Page<Book> list(Pageable pageable) {
        log.info(dataUtil.fomartLocalDateTimeToBaseStyle(LocalDateTime.now()));
        return serviceBook.listAll(pageable);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Book>> listAllNonPageable( ) {
        log.info(dataUtil.fomartLocalDateTimeToBaseStyle(LocalDateTime.now()));
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
