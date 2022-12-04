package ru.kaznacheev.system.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kaznacheev.system.repository.UserRepository;
import ru.kaznacheev.system.service.UserService;
import ru.kaznacheev.system.util.UserValidator;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private UserService userService;
    private UserValidator userValidator;
    private UserRepository userRepository;

    @Autowired
    public ProfileController(UserService userService, UserRepository userRepository, UserValidator userValidator) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @GetMapping("/{id}")
    public String getProfilePage(@PathVariable("id") int id) {
        return "profile";
    }



}
