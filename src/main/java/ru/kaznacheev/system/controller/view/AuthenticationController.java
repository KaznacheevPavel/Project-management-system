package ru.kaznacheev.system.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.service.UserService;
import ru.kaznacheev.system.util.UserValidator;
import ru.kaznacheev.system.util.ValidatorMarker;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public AuthenticationController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest request) {
        return "authorization";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Validated(ValidatorMarker.Create.class) User user,
                               BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.registration(user);
        return "redirect:/login";
    }

}
