package com.example.BBookstore;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.BBookstore.domain.Book;
import com.example.BBookstore.domain.BookRepository;
import com.example.BBookstore.domain.Category;
import com.example.BBookstore.domain.CategoryRepository;

@SpringBootTest
@Transactional
public class BBookstoreApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testCreateAndFindBook() {
        
        Category category = new Category();
        category.setName("Fiction");
        category = categoryRepository.save(category);

        
        Book book = new Book();
        book.setTitle("Spring Boot in Action");
        book.setAuthor("Craig Walls");
        book.setPublicationYear(2016);
        book.setIsbn("9781617292545");
        book.setPrice(39.99);
        book.setCategory(category);
        book = bookRepository.save(book);

       
        Optional<Book> foundBook = bookRepository.findById(book.getId());
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("Spring Boot in Action");
    }

    @Test
    void testDeleteBook() {
        
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book = bookRepository.save(book);

        
        bookRepository.deleteById(book.getId());

        
        Optional<Book> deletedBook = bookRepository.findById(book.getId());
        assertThat(deletedBook).isEmpty();
    }
}