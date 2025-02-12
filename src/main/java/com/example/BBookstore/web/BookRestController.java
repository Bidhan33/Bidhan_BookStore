package com.example.BBookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BBookstore.domain.Book;
import com.example.BBookstore.domain.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    // Get book by ID
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }

    // Add a new book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Update an existing book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long bookId, @RequestBody Book updatedBook) {
        return bookRepository.findById(bookId)
            .map(book -> {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPublicationYear(updatedBook.getPublicationYear());
                book.setIsbn(updatedBook.getIsbn());
                book.setPrice(updatedBook.getPrice());
                book.setCategory(updatedBook.getCategory());
                return bookRepository.save(book);
            })
            .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + bookId));
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
