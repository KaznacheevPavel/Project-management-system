package ru.kaznacheev.system.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping("/profile/{username}")
    public String profilePage() {
        return "profile";
    }

}
