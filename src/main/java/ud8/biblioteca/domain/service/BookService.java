package ud8.biblioteca.domain.service;

import ud8.biblioteca.domain.entity.Book;
import ud8.common.exception.ResourceNotFoundException;

import java.util.List;

public interface BookService {
    Book findById(int id) throws ResourceNotFoundException;
    List<Book> findAll();
    List<Book> findByGenre(String genre);
    Book addBook(Book book);
    void deleteBook(int id) throws ResourceNotFoundException;
    Book borrowBook(int id) throws ResourceNotFoundException;
    Book returnBook(int id) throws ResourceNotFoundException;
}
