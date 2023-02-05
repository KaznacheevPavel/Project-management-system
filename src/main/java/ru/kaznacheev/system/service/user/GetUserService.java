package ru.kaznacheev.system.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GetUserService {

    private final UserRepository userRepository;

    @Autowired
    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

}
