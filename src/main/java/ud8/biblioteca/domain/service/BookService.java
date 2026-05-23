package ud8.biblioteca.domain.service;

import ud8.biblioteca.domain.entity.Book;

import java.util.List;

public interface BookService {
    Book findById(int id);
    List<Book> findAll();
    List<Book> findByGenre(String genre);
    Book addBook(Book book);
    void deleteBook(int id);
    Book borrowBook(int id);
    Book returnBook(int id);
}
