package ru.kaznacheev.system.validator.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kaznacheev.system.dto.user.PasswordChangeDTO;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.service.user.GetUserService;

@Component
public class ChangePasswordValidator implements Validator {

    private final GetUserService getUserService;
    private final PasswordEncoder encoder;

    public ChangePasswordValidator(GetUserService getUserService, PasswordEncoder encoder) {
        this.getUserService = getUserService;
        this.encoder = encoder;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PasswordChangeDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordChangeDTO passwordInfo = (PasswordChangeDTO) target;
        if (passwordInfo.getOldPassword().equals(passwordInfo.getNewPassword())) {
            errors.rejectValue("newPassword", "", "Пароли одинаковы");
        }

        User user = getUserService.getUserById(passwordInfo.getId());
        if (!encoder.matches(passwordInfo.getOldPassword(), user.getPassword())) {
            errors.rejectValue("oldPassword", "", "Неверный текущий пароль");
        }
    }
}
