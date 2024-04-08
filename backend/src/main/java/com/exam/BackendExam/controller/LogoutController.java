package com.exam.BackendExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.exam.BackendExam.entities.UserEntity;

@Controller
public class LogoutController {

	@GetMapping("/login")
    public String logOut(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }
}