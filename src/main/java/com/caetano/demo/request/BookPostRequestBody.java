package com.caetano.demo.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookPostRequestBody {
    @NotEmpty(message = "the Book cannot is Empty or null")
    private String nameBook;
}
