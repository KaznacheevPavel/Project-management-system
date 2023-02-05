package ru.kaznacheev.system.service.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import ru.kaznacheev.system.dto.user.UserDTO;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.repository.UserRepository;
import ru.kaznacheev.system.util.error.ResponseException;
import ru.kaznacheev.system.validator.user.UpdateValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserUpdateService {

    private final GetUserService getUserService;
    private final UpdateValidator validator;
    private final UserRepository userRepository;

    public UserUpdateService(GetUserService getUserService, UpdateValidator validator, UserRepository userRepository) {
        this.getUserService = getUserService;
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @Transactional
    public void update(@Valid UserDTO userDTO) {
        List<String> errors = validate(userDTO);
        if (errors.isEmpty()) {
            User user = getUserService.getUserById(userDTO.getId());
            changeField(user, userDTO);
            userRepository.save(user);
        } else {
            throw new ResponseException(errors, HttpStatus.BAD_REQUEST);
        }
    }

    private List<String> validate(UserDTO userDTO) {
        DataBinder dataBinder = new DataBinder(userDTO);
        BindingResult bindingResult = dataBinder.getBindingResult();
        validator.validate(userDTO, bindingResult);
        List<String> errors = new ArrayList<>();
        if (dataBinder.getBindingResult().hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
        }
        return errors;
    }

    private void changeField(User user, UserDTO userDTO) {
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
    }

}
