package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping
    public String signUp(@ModelAttribute User user, Model model) {

        Optional<User> optionalUser = Optional.ofNullable(userService.getUser(user.getUsername()));

        optionalUser.ifPresentOrElse(
                existingUser -> model.addAttribute("signupError", "User already exists!"),
                () -> {
                    userService.createUser(user);
                    model.addAttribute("signupSuccess", true);
                });
        return "signup";
    }
}
