package com.examBE.BackendExamSys.services;

import com.examBE.BackendExamSys.configs.UserSecurity;
import com.examBE.BackendExamSys.entities.UserEntity;
import com.examBE.BackendExamSys.entities.UserOutEntity;
import com.examBE.BackendExamSys.models.Dto.UserDto;
import com.examBE.BackendExamSys.models.Dto.UserDtoOut;
import com.examBE.BackendExamSys.models.LoginModel;
import com.examBE.BackendExamSys.models.UserModel;
import com.examBE.BackendExamSys.utils.ErrorException;

import java.util.List;

public interface UserService {
    UserOutEntity loginAdmin(LoginModel loginModel);
    List<UserModel> getAllUser();
    List<UserDtoOut> getAllStudent();
    UserModel searchById (int id) throws ErrorException;
    UserModel searchByEmail (String email) throws ErrorException;
    void register(UserDto userDto) throws ErrorException;
    void registerAdmin(UserDto userDto) throws ErrorException;
    void registerStudent(UserDto userDto) throws ErrorException;
    void edit(UserEntity user) throws ErrorException;
    void delete(int id) throws ErrorException;
}
