package ud8.users.domain.service;

import ud8.users.domain.entity.User;
import ud8.common.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    User findById(int id) throws ResourceNotFoundException;
    List<User> findAll();
    boolean create(User user);
    boolean update(User user);
    boolean delete(int id);

}