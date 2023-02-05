package ru.kaznacheev.system.service.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import ru.kaznacheev.system.dto.user.RegisteringUserDTO;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.mapper.impl.RegisteringUserMapperImpl;
import ru.kaznacheev.system.repository.UserRepository;
import ru.kaznacheev.system.validator.user.RegistrationValidator;
import ru.kaznacheev.system.util.error.ResponseException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Transactional(readOnly = true)
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationValidator validator;
    private final RegisteringUserMapperImpl mapper;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, RegistrationValidator validator, RegisteringUserMapperImpl mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Transactional
    public void registration(@Valid RegisteringUserDTO registeringUser) {
        List<String> errors = validate(registeringUser);
        if (errors.isEmpty()) {
            User user = mapper.toUser(registeringUser);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new ResponseException(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private List<String> validate(RegisteringUserDTO registeringUser) {
        DataBinder dataBinder = new DataBinder(registeringUser);
        BindingResult bindingResult = dataBinder.getBindingResult();
        validator.validate(registeringUser, bindingResult);
        List<String> errors = new ArrayList<>();
        if (dataBinder.getBindingResult().hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
        }
        return errors;
    }

}
