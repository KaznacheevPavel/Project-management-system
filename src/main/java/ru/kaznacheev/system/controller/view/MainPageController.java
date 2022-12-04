package ru.kaznacheev.system.controller.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kaznacheev.system.security.UserDetailsImpl;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MainPageController {

    @GetMapping("/suc")
    public String successAuth(Model model, Authentication authentication, HttpServletResponse response) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        return "mainPage";
    }

}
