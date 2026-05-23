package ud8.users.domain.service.impl;

import ud8.common.exception.ResourceNotFoundException;
import ud8.users.domain.entity.User;
import ud8.users.domain.service.UserService;
import ud8.users.persistance.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // --8<-- [start:findById]
    @Override
    public User findById(int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    // --8<-- [end:findById]

    // --8<-- [start:isEmailValid]
    public boolean isEmailValid(String email){
        String pattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(pattern);
    }
    // --8<-- [end:isEmailValid]

    // --8<-- [start:create]
    @Override
    public boolean create(User user) {
        if (!isEmailValid(user.getEmail()))
            return false;

        if (userRepository.existsByEmail(user.getEmail()))
            return false;

        userRepository.create(user);
        return true;
    }
    // --8<-- [end:create]

    @Override
    public boolean update(User user) {
        if (!userRepository.existsById(user.getId())) {
            return false;
        }
        userRepository.update(user);
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.delete(id);
        return true;
    }


}
