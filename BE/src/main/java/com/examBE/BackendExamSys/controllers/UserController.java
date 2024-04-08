package com.examBE.BackendExamSys.controllers;

import com.examBE.BackendExamSys.configs.AuthService;
import com.examBE.BackendExamSys.configs.UserSecurity;
import com.examBE.BackendExamSys.entities.UserEntity;
import com.examBE.BackendExamSys.entities.UserOutEntity;
import com.examBE.BackendExamSys.models.Dto.UserDto;
import com.examBE.BackendExamSys.models.Dto.UserDtoOut;
import com.examBE.BackendExamSys.models.LoginModel;
import com.examBE.BackendExamSys.models.UserModel;
import com.examBE.BackendExamSys.models.ApiResult;
import com.examBE.BackendExamSys.services.UserService;
import com.examBE.BackendExamSys.utils.Constants;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/loginAdmin")
    public ResponseEntity<ApiResult<UserOutEntity>> login(@RequestBody LoginModel loginModel) {
       UserOutEntity user = userService.loginAdmin(loginModel);
       if(user == null) return ResponseEntity.ok(ApiResult.create(HttpStatus.CONFLICT, "Login Fail", null));
       return ResponseEntity.ok(ApiResult.create(HttpStatus.OK, "Login success", user));
    }

    @GetMapping(path = "/list")
    public ResponseEntity<ApiResult<List<UserModel>>> showList(@RequestParam(name = "authId") int authId) {
        List<UserModel> userList = userService.getAllUser();
        String message = "Success";
        if (userList.isEmpty()) {
            message = "Empty";
        }
        ApiResult<List<UserModel>> result = ApiResult.create(HttpStatus.OK, message, userList);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/listStudent")
    public ResponseEntity<ApiResult<List<UserDtoOut>>> showListStudent(@RequestParam(name = "authId") Integer authId) {
        if(authId == null || (authId!=null && !authService.AuthUser(authId,"admin"))){
            return ResponseEntity.ok(ApiResult.create(HttpStatus.CONFLICT, "No Permit", null));
        }

        List<UserDtoOut> userList = userService.getAllStudent();
        String message = "Success";
        if (userList.isEmpty()) {
            message = "Empty";
        }
        ApiResult<List<UserDtoOut>> result = ApiResult.create(HttpStatus.OK, message, userList);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/searchById")
    public ResponseEntity<ApiResult<UserModel>> searchById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ApiResult<UserModel> result = null;
        try{
            UserModel user = userService.searchById(id);
            result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, user);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/searchByEmail")
    public ResponseEntity<ApiResult<UserModel>> searchByEmail(@RequestParam(name = "email", required = false, defaultValue = "") String email) {
        ApiResult<UserModel> result = null;
        try{
            UserModel user = userService.searchByEmail(email);
            result = ApiResult.create(HttpStatus.OK, Constants.SEARCH_DATA_SUCCESS, user);
        }
        catch (Exception ex) {
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResult<UserDto>> registerUser(@Valid @RequestBody UserDto userDto) {
        ApiResult<UserDto> result = null;
        try{
            userService.register(userDto);
            result = ApiResult.create(HttpStatus.OK, Constants.SAVE_DATA_SUCCESS, userDto);
        }
        catch (Exception ex){
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/registerAdmin")
    public ResponseEntity<ApiResult<UserDto>> registerAdmin(@Valid @RequestBody UserDto userDto) {
        ApiResult<UserDto> result = null;
        try{
            userService.registerAdmin(userDto);
            result = ApiResult.create(HttpStatus.OK, Constants.SAVE_DATA_SUCCESS, userDto);
        }
        catch (Exception ex){
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/registerStudent")
    public ResponseEntity<ApiResult<UserDto>> registerStudent(@Valid @RequestBody UserDto userDto) {
        ApiResult<UserDto> result = null;
        try{
            userService.registerStudent(userDto);
            result = ApiResult.create(HttpStatus.OK, Constants.SAVE_DATA_SUCCESS, userDto);
        }
        catch (Exception ex){
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ApiResult<UserEntity>> EditUser(@Valid @RequestBody UserEntity userEntity) {
        ApiResult<UserEntity> result = null;
        try{
            userService.edit(userEntity);
            result = ApiResult.create(HttpStatus.OK, Constants.EDIT_DATA_SUCCESS, userEntity);
        }
        catch (Exception ex){
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), null);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ApiResult<Integer>> EditUser(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
        ApiResult<Integer> result = null;
        try{
            userService.delete(id);
            result = ApiResult.create(HttpStatus.OK, Constants.DELETE_DATA_SUCCESS, id);
        }
        catch (Exception ex){
            result = ApiResult.create(HttpStatus.CONFLICT, ex.getMessage(), id);
        }
        return ResponseEntity.ok(result);
    }
}
