package com.example.Service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Exception.BookNotFoundException;
import com.example.Model.Book;

@Service
public class BookService {
	private final ArrayList<Book> books=new ArrayList<>();
	private long nextId=1;
	
	//copy a book
	private Book copyBook(Book book) {
		return new Book(
				book.getBookId(),
				book.getBookName(),
				book.getAuthor(),
				book.getPrice(),
				book.isAvailable()
				);
		}
	
	//Add a book
	public Book addBook(Book request) {
		Book book=new Book(
				nextId++,
				request.getBookName().trim(),
				request.getAuthor().trim(),
				request.getPrice(),
				request.isAvailable()
				);
		books.add(book);
		return copyBook(book);
		
	}
	
	//Get all books
	public List<Book> getAllBooks(){
		return books.stream()
				.map(this::copyBook)
				.toList();
		
	}
	
	//Search book by ID
	public Book getBookById(Long id) {
		Book book= books.stream()
				.filter(item -> item.getBookId().equals(id))
				.findFirst()
				.orElseThrow(()->
				new BookNotFoundException(
						"Book not found with ID:"+id
						));
		return copyBook(book);
		
	}
	// Update a book
	public Book updateBook(Long id, Book request) {

	    Book existingBook = books.stream()
	            .filter(item -> item.getBookId().equals(id))
	            .findFirst()
	            .orElseThrow(() ->
	                new BookNotFoundException(
	                    "Book not found with ID: " + id
	                ));

	    existingBook.setBookName(request.getBookName().trim());
	    existingBook.setAuthor(request.getAuthor().trim());
	    existingBook.setPrice(request.getPrice());
	    existingBook.setAvailable(request.isAvailable());

	    return copyBook(existingBook);
	}
	// Update book availability
    public Book updateAvailability(Long id, boolean available) {

        Book book = books.stream()
                .filter(item -> item.getBookId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new BookNotFoundException(
                        "Book not found with ID: " + id
                    ));

        book.setAvailable(available);
        return copyBook(book);
    }
     // Check book availability
    public boolean isBookAvailable(Long id) {

        Book book = books.stream()
                .filter(item -> item.getBookId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new BookNotFoundException(
                        "Book not found with ID: " + id
                    ));

        return book.isAvailable();
    }
    // Delete a book
    public void deleteBook(Long id) {

        Book book = books.stream()
                .filter(item -> item.getBookId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new BookNotFoundException(
                        "Book not found with ID: " + id
                    ));

        books.remove(book);
    }

    // Count books
    public int getBookCount() {
        return books.size();
    }
}

	

