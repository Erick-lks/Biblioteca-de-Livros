package com.caetano.BooksManeger.mapper;


import com.caetano.BooksManeger.domain.Book;
import com.caetano.BooksManeger.request.BookPostRequestBody;
import com.caetano.BooksManeger.request.BookPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
    public abstract class BookMapper {
    public static final BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    public abstract Book toBook(BookPostRequestBody bookPostRequestBody);

    public abstract Book toBook(BookPutRequestBody bookPutRequestBody);
}
