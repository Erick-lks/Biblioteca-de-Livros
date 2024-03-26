package com.caetano.BooksManeger.request;


import lombok.Data;

@Data
public class BookPutRequestBody {
    private Long id;
    private String nameBook;


}
