package ru.kaznacheev.system.validator.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kaznacheev.system.dto.user.UserDTO;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.repository.UserRepository;

import java.util.Optional;

@Component
public class UpdateValidator implements Validator {

    private final UserRepository repository;

    public UpdateValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        Optional<User> user = repository.findByUsername(userDTO.getUsername());
        if (user.isPresent() && user.get().getId() != userDTO.getId()) {
            errors.rejectValue("username", "", "Такой логин уже используется");
        }
        user = repository.findByEmail(userDTO.getEmail());
        if (user.isPresent() && user.get().getId() != userDTO.getId()) {
            errors.rejectValue("email", "", "Такой email уже используется");
        }

    }
}
