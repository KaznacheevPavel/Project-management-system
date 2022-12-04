package ru.kaznacheev.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    @Transactional
    public void registration(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setPassword(userRepository.findById(id).get().getPassword());
        userRepository.save(updatedUser);
    }

    @Transactional
    public void changePassword(int id, String newPassword) {
        newPassword = passwordEncoder.encode(newPassword);
        User user = userRepository.findById(id).get();
        user.setPassword(newPassword);
    }

}
