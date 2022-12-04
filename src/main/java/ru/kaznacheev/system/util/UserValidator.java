package ru.kaznacheev.system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.repository.UserRepository;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User targetUser = (User) target;
        System.out.println(targetUser);

        Optional<User> user = userRepository.findByUsername(targetUser.getUsername());

        if (user.isPresent() && user.get().getId() != targetUser.getId()) {
            errors.rejectValue("username", "", "Логин уже занят");
        }

        user = userRepository.findByEmail(targetUser.getEmail());

        if (user.isPresent()  && user.get().getId() != targetUser.getId()) {
            errors.rejectValue("email", "", "Почта уже используется");
        }

    }
}
