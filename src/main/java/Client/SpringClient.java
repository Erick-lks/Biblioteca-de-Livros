package Client;

import com.caetano.BooksManeger.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<List<Book>> listExchange = new RestTemplate().exchange("http://localhost:8080/books/list",
                HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Book>>() {
                });
        log.info(listExchange.getBody());

        Book alive = Book.builder().nameBook("Alive").build();
        ResponseEntity<Book> aliveSaved = new RestTemplate().exchange("http://localhost:8080/books",
                HttpMethod.POST,
                new HttpEntity<>(alive, createJsonHeader()), Book.class);

        log.info("Saved Book {}", aliveSaved);

        Book bookToUpdate = aliveSaved.getBody();
        bookToUpdate.setNameBook("Survive");

        ResponseEntity<Void> aliveToUpdate = new RestTemplate().exchange("http://localhost:8080/books",
                HttpMethod.PUT,
                new HttpEntity<>(bookToUpdate, createJsonHeader()), Void.class);

        log.info(aliveToUpdate);


         ResponseEntity<Void> bookDelete = new RestTemplate().exchange("http://localhost:8080/books/{id}",
                HttpMethod.DELETE,
                null, Void.class, bookToUpdate.getId());

         log.info(bookDelete);

    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
