package ud8.biblioteca.domain.service.impl;

import ud8.biblioteca.domain.entity.Book;
import ud8.biblioteca.domain.service.BookService;
import ud8.biblioteca.domain.service.NotificationService;
import ud8.biblioteca.persistance.repository.BookRepository;
import ud8.common.exception.ResourceNotFoundException;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final NotificationService notificationService;

    public BookServiceImpl(BookRepository bookRepository, NotificationService notificationService) {
        this.bookRepository = bookRepository;
        this.notificationService = notificationService;
    }

    /**
     * Returns a book by its id.
     * @throws ResourceNotFoundException if the book does not exist.
     */
    @Override
    public Book findById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    /**
     * Returns all books in the repository.
     */
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Returns all books matching the given genre.
     */
    @Override
    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    /**
     * Saves a new book and sends an "added" notification.
     */
    @Override
    public Book addBook(Book book) {
        Book saved = bookRepository.save(book);
        notificationService.notifyBookAdded(book.getTitle());
        return saved;
    }

    /**
     * Deletes a book by id.
     * @throws ResourceNotFoundException if the book does not exist.
     */
    @Override
    public void deleteBook(int id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(id);
    }

    /**
     * Marks a book as borrowed (available = false).
     * @throws ResourceNotFoundException if the book does not exist.
     * @throws IllegalStateException if the book is already borrowed (not available).
     */
    @Override
    public Book borrowBook(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available: " + book.getTitle());
        }
        book.setAvailable(false);
        Book updated = bookRepository.save(book);
        notificationService.notifyBookBorrowed(book.getTitle());
        return updated;
    }

    /**
     * Marks a book as returned (available = true).
     * @throws ResourceNotFoundException if the book does not exist.
     * @throws IllegalStateException if the book is already available (not borrowed).
     */
    @Override
    public Book returnBook(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        if (book.isAvailable()) {
            throw new IllegalStateException("Book is already available: " + book.getTitle());
        }
        book.setAvailable(true);
        Book updated = bookRepository.save(book);
        notificationService.notifyBookReturned(book.getTitle());
        return updated;
    }
}
