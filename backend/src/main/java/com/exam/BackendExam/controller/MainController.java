package com.exam.BackendExam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.BackendExam.entities.*;
import com.exam.BackendExam.repository.*;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamRepository examRepository;

    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }
    
   
    
    @PostMapping("/login")
    public String login(@ModelAttribute("user") UserEntity user, Model model) {
        UserEntity existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("loginSuccess", true);
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", true);
            return "login";
        }
    }
    
    @GetMapping("/home")
    public String showHome(Model model) {
        List<ExamEntity> exams = examRepository.findAll();
        model.addAttribute("exams", exams);
        return "home";
    }
    
    
}
