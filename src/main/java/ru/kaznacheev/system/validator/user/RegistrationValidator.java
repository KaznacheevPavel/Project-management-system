package ru.kaznacheev.system.validator.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kaznacheev.system.dto.user.RegisteringUserDTO;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.repository.UserRepository;

import java.util.Optional;

@Component
public class RegistrationValidator implements Validator {

    private final UserRepository userRepository;

    public RegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegisteringUserDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisteringUserDTO registeringUser = (RegisteringUserDTO) target;

        Optional<User> user = userRepository.findByUsername(registeringUser.getUsername());

        if (user.isPresent()) {
            errors.rejectValue("username", "", "Логин занят");
        }

        user = userRepository.findByEmail(registeringUser.getEmail());

        if (user.isPresent()) {
            errors.rejectValue("email", "", "Такой email уже зарегистрирован");
        }
    }
}
