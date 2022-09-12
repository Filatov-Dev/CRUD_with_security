package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Security.UserDetailsImp;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp personDetails = (UserDetailsImp) authentication.getPrincipal();
        User user = personDetails.getUser();
        model.addAttribute("user", user);
        return "admin/admin_main";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.showAllUsers());
        return "admin/all_users";
    }

    @GetMapping("/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUserInfo(id));
        return "admin/user_info";
    }

    @GetMapping("/new")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") User newUser) {
        userService.saveUser(newUser);
        return "redirect:/auth/login";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.showUserInfo(id));
        return "admin/edit_user";
    }

    @PatchMapping("/{id}")
    public String updateUserInfo(@ModelAttribute("user") User user,@PathVariable("id") int id , Model model) {
        userService.updateUserInfo(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
