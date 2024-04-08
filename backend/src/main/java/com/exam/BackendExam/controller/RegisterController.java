package com.exam.BackendExam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.exam.BackendExam.entities.UserEntity;
import com.exam.BackendExam.repository.UserRepository;

@Controller

public class RegisterController {

    @Autowired
    private UserRepository userRepository; 

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register"; 
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserEntity user) {
        userRepository.save(user);
        return "redirect:/login"; 
    }
}
