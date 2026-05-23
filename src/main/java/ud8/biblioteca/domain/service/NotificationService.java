package ud8.biblioteca.domain.service;

public interface NotificationService {
    void notifyBookAdded(String bookTitle);
    void notifyBookBorrowed(String bookTitle);
    void notifyBookReturned(String bookTitle);
}
