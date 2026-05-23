package ud8.users.persistance.dao;

import ud8.users.domain.entity.User;

import java.util.List;

public interface UserDao {
    User findById(int userId);
    User findByEmail(String email);
    List<User> findAll();
    int insert(User user);
    int update(User user);
    int delete(int userId);
}
