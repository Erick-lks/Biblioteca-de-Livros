package com.caetano.demo.service;

import com.caetano.demo.domain.Book;

import com.caetano.demo.exceptions.BadRequestException;
import com.caetano.demo.mapper.BookMapper;
import com.caetano.demo.repository.BookRepository;
import com.caetano.demo.request.BookPostRequestBody;
import com.caetano.demo.request.BookPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceBook {

    private final BookRepository bookRepository;



    public Page<Book> listAll (Pageable pageable){
        return bookRepository.findAll(pageable);
    }


    public List<Book> listAllNonPageable() {
        return bookRepository.findAll();
    }
    public List<Book> findByNameBook (String nameBook){
        return bookRepository.findByNameBook(nameBook);

    }

    public Book findByIdOrThrowBadRequestException(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException( "Book not Found"));
    }


    @Transactional
   public Book save (BookPostRequestBody bookPostRequestBody) {
      return bookRepository.save(BookMapper.INSTANCE.toBook(bookPostRequestBody));

   }

    public void remove(Long id) {
        bookRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(BookPutRequestBody bookPutRequestBody) {
        Book savedBook = findByIdOrThrowBadRequestException(bookPutRequestBody.getId());
        Book book = BookMapper.INSTANCE.toBook(bookPutRequestBody);
        book.setId(savedBook.getId());
        bookRepository.save(book);

    }

}


