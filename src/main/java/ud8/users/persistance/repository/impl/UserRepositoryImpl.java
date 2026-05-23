package ud8.users.persistance.repository.impl;

import ud8.users.domain.entity.User;
import ud8.users.persistance.dao.UserDao;
import ud8.users.persistance.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Override
    public boolean existsById(int userId) {
        return userDao.findById(userId) != null;
    }

    @Override
    public boolean create(User user) {
        return userDao.insert(user) == 1;
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user) == 1;
    }

    @Override
    public boolean delete(int userId) {
        return userDao.delete(userId) == 1;
    }
}
