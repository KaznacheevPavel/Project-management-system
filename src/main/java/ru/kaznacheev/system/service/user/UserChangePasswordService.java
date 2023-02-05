package ru.kaznacheev.system.service.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import ru.kaznacheev.system.dto.user.PasswordChangeDTO;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.repository.UserRepository;
import ru.kaznacheev.system.util.error.ResponseException;
import ru.kaznacheev.system.validator.user.ChangePasswordValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class UserChangePasswordService {

    private final GetUserService getUserService;
    private final UserRepository userRepository;
    private final ChangePasswordValidator validator;
    private final PasswordEncoder encoder;

    public UserChangePasswordService(GetUserService getUserService, UserRepository userRepository, ChangePasswordValidator validator, PasswordEncoder encoder) {
        this.getUserService = getUserService;
        this.userRepository = userRepository;
        this.validator = validator;
        this.encoder = encoder;
    }

    @Transactional
    public void changePassword(@Valid PasswordChangeDTO passwordChangeDTO) {
        List<String> errors = validatePasswordChanges(passwordChangeDTO);
        if (!errors.isEmpty()) {
            throw new ResponseException(errors, HttpStatus.BAD_REQUEST);
        }
        User user = getUserService.getUserById(passwordChangeDTO.getId());
        user.setPassword(encoder.encode(passwordChangeDTO.getNewPassword()));
        userRepository.save(user);
    }

    private List<String> validatePasswordChanges(PasswordChangeDTO passwordChangeDTO) {
        DataBinder dataBinder = new DataBinder(passwordChangeDTO);
        BindingResult bindingResult = dataBinder.getBindingResult();
        validator.validate(passwordChangeDTO, bindingResult);
        List<String> errors = new ArrayList<>();
        if (dataBinder.getBindingResult().hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
        }
        return errors;
    }

}
