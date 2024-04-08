package com.examBE.BackendExamSys.models;

import com.examBE.BackendExamSys.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private int id;
    private String name;
    private Boolean gender;

    private String email;
    private String password;
    private String roles;
    public static UserModel toUsersModel(UserEntity userEntity){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userEntity, userModel);
        return userModel;
    }
}

