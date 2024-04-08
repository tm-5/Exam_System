package com.examBE.BackendExamSys.controllers;

import com.examBE.BackendExamSys.models.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/login")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoginController {
    @GetMapping(path = "")
    public ResponseEntity<ApiResult<String>> login() {
        ApiResult<String> result = ApiResult.create(HttpStatus.CONFLICT, "No Permit", "123");
        return ResponseEntity.ok(result);
    }
}
