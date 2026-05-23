package ud8.users.persistance.repository;

import ud8.users.domain.entity.User;

import java.util.List;

public interface UserRepository {
    User findById(int userId);
    List<User> findAll();
    boolean existsByEmail(String email);
    boolean existsById(int userId);
    boolean create(User user);
    boolean update(User user);
    boolean delete(int userId);
}
