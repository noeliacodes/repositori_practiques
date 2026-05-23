package ud8.biblioteca.persistance.repository;

import ud8.biblioteca.domain.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(int id);
    List<Book> findAll();
    List<Book> findByGenre(String genre);
    Book save(Book book);
    void delete(int id);
}
