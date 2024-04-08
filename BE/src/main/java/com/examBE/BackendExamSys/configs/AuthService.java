package com.examBE.BackendExamSys.configs;


import com.examBE.BackendExamSys.entities.UserEntity;
import com.examBE.BackendExamSys.repositories.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final UserRep userRep;
    public boolean AuthUser(int id, String role){
        UserEntity user = userRep.findFirstById(id);
        if(user == null) return false;
        if(user.getRoles().contentEquals(role))
            return true;
        return false;
    }
}
