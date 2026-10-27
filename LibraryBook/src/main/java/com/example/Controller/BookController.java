package com.example.Controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.Model.Book;
import com.example.Service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Add a book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Search book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    // Update a book
    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @Valid @RequestBody Book book) {

        return bookService.updateBook(id, book);
    }

    // Update availability
    @PatchMapping("/{id}/availability")
    public Book updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {
        return bookService.updateAvailability(id, available);
    }
     // Check book availability
    @GetMapping("/{id}/availability")
    public String checkAvailability(@PathVariable Long id) {

        boolean available = bookService.isBookAvailable(id);

        if (available) {
            return "Book is available";
        } else {
            return "Book is not available";
        }
    }

    // Delete a book
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Count books
    @GetMapping("/count")
    public int getBookCount() {
        return bookService.getBookCount();
    }
}