package com.example.BBookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.example.BBookstore.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetBooks() {
        ResponseEntity<Book[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/books", Book[].class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void testCreateAndDeleteBook() {
        Book book = new Book();
        book.setTitle("New API Book");
        book.setAuthor("Author API");
        book.setPublicationYear(2022);
        book.setIsbn("1234567890");
        book.setPrice(49.99);

       
        ResponseEntity<Book> response = restTemplate.postForEntity("http://localhost:" + port + "/api/books", book, Book.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        Book createdBook = response.getBody();
        assertThat(createdBook).isNotNull();

        
        restTemplate.delete("http://localhost:" + port + "/api/books/" + createdBook.getId());

       
        ResponseEntity<Book> deleteResponse = restTemplate.getForEntity("http://localhost:" + port + "/api/books/" + createdBook.getId(), Book.class);
        assertThat(deleteResponse.getBody()).isNull();
    }
}