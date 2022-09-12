package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Security.UserDetailsImp;
import ru.kata.spring.boot_security.demo.model.User;


@Controller
public class UserController {
    @GetMapping("/user")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp personDetails = (UserDetailsImp) authentication.getPrincipal();
        User user = personDetails.getUser();
        model.addAttribute("user", user);
        return "users/user_info";
    }

}
