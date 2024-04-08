package com.examBE.BackendExamSys.services.Impls;

import com.examBE.BackendExamSys.configs.UserSecurity;
import com.examBE.BackendExamSys.entities.UserEntity;
import com.examBE.BackendExamSys.entities.UserOutEntity;
import com.examBE.BackendExamSys.models.Dto.UserDtoOut;
import com.examBE.BackendExamSys.models.LoginModel;
import com.examBE.BackendExamSys.models.UserModel;
import com.examBE.BackendExamSys.models.Dto.UserDto;
import com.examBE.BackendExamSys.repositories.UserRep;
import com.examBE.BackendExamSys.services.UserService;
import com.examBE.BackendExamSys.utils.ErrorException;
import com.examBE.BackendExamSys.utils.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static java.text.MessageFormat.*;

@Component
@RequiredArgsConstructor
public class UserServicesImpl implements UserService {
    private final UserRep userRep;
    private UserEntity ToUserEntity(UserDto userDto){
        return UserEntity.create(
                0,
                userDto.getName(),
                userDto.getGender(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRoles()
        );
    }

    private UserDtoOut ToUserDtoOut(UserEntity userEntity){
        return UserDtoOut.create(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getGender(),
                userEntity.getEmail()
        );
    }

    @Override
    public UserOutEntity loginAdmin(LoginModel loginModel) {
        UserEntity userEntity = userRep.findFirstByEmail(loginModel.getUsername());
        if(userEntity == null) return null;
        if(userEntity.getPassword().contentEquals(loginModel.getPassword()) && userEntity.getRoles().contentEquals("admin")){
            return UserOutEntity.create(userEntity.getId(),userEntity.getName(),userEntity.getGender(),userEntity.getEmail());
        }
        return null;
    }

    @Override
    public List<UserModel> getAllUser() {
        List<UserModel> listUser = new ArrayList<>();
        List<UserEntity> listUserEntity = userRep.findAll();
        for(UserEntity user: listUserEntity){
            listUser.add(UserModel.toUsersModel(user));
        }
        return listUser;
    }

    @Override
    public List<UserDtoOut> getAllStudent() {
        List<UserEntity> listUserEntity = userRep.findAllByRoles("student");
        List<UserDtoOut> studentList = new ArrayList<>();
        for(UserEntity user: listUserEntity){
            studentList.add(ToUserDtoOut(user));
        }
        return studentList;
    }

    @Override
    public UserModel searchById(int id) throws ErrorException {
        UserEntity userEntity = userRep.findFirstById(id);
        if(userEntity == null) throw new ErrorException(Constants.USER_NOT_FOUND);
        return UserModel.toUsersModel(userEntity);
    }
    @Override
    public UserModel searchByEmail(String email) throws ErrorException {
        UserEntity userEntity = userRep.findFirstByEmail(email);
        if(userEntity == null) throw new ErrorException(Constants.USER_NOT_FOUND);
        return UserModel.toUsersModel(userEntity);
    }
    @Override
    public void register(UserDto userDto) throws ErrorException {
        if(Valids.isEmpty(userDto.getName())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Name"));
        if(Valids.isEmpty(userDto.getGender())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Gender"));
        if(Valids.isEmpty(userDto.getEmail())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Email"));
        if(Valids.isEmpty(userDto.getPassword())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Password"));
        if(userRep.existsByEmail(userDto.getEmail())) throw new ErrorException(MessageFormat.format(Constants.DUPLICATE_EMAIL,userDto.getEmail()));
        UserEntity userEntity = ToUserEntity(userDto);
        userRep.save(userEntity);
    }
    @Override
    public void registerAdmin(UserDto userDto) throws ErrorException {
        if(Valids.isEmpty(userDto.getName())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Name"));
        if(Valids.isEmpty(userDto.getGender())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Gender"));
        if(Valids.isEmpty(userDto.getEmail())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Email"));
        if(Valids.isEmpty(userDto.getPassword())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Password"));
        if(userRep.existsByEmail(userDto.getEmail())) throw new ErrorException(MessageFormat.format(Constants.DUPLICATE_EMAIL,userDto.getEmail()));
        UserEntity userEntity = ToUserEntity(userDto);
        userEntity.setRoles("admin");
        userRep.save(userEntity);
    }
    @Override
    public void registerStudent(UserDto userDto) throws ErrorException {
        if(Valids.isEmpty(userDto.getName())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Name"));
        if(Valids.isEmpty(userDto.getGender())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Gender"));
        if(Valids.isEmpty(userDto.getEmail())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Email"));
        if(Valids.isEmpty(userDto.getPassword())) throw new ErrorException(MessageFormat.format(Constants.INVALID_EMPTY, "Password"));
        if(userRep.existsByEmail(userDto.getEmail())) throw new ErrorException(MessageFormat.format(Constants.DUPLICATE_EMAIL,userDto.getEmail()));
        UserEntity userEntity = ToUserEntity(userDto);
        userEntity.setRoles("student");
        userRep.save(userEntity);
    }

    @Override
    public void edit(UserEntity newInfor) throws ErrorException {
        UserEntity user = userRep.findFirstById(newInfor.getId());
        if (user == null) {
            throw new ErrorException(Constants.USER_NOT_FOUND);
        }
        if(!Valids.isEmpty(newInfor.getGender())) user.setGender(newInfor.getGender());
        if(!Valids.isEmpty(newInfor.getName())) user.setName(newInfor.getName());
        if(!Valids.isEmpty(newInfor.getPassword())) user.setPassword(newInfor.getPassword());

        if (!Valids.isEmpty(newInfor.getEmail())) {
            UserEntity userCheck = userRep.findFirstByEmail(newInfor.getEmail());
            if(userCheck!=null && userCheck.getId() != user.getId())
                throw new ErrorException(MessageFormat.format(Constants.DUPLICATE_EMAIL,newInfor.getEmail()));
            else user.setEmail(newInfor.getEmail());
        }
        userRep.save(user);
    }
    @Override
    public void delete(int id) throws ErrorException {
        if(!userRep.existsById(id)) throw new ErrorException("Khong tim thay du lieu phu hop");
        userRep.deleteById(id);
    }
}
