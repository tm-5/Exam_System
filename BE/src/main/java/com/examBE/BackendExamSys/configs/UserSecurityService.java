package com.examBE.BackendExamSys.configs;

import com.examBE.BackendExamSys.entities.UserEntity;
import com.examBE.BackendExamSys.repositories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRep userRep;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRep.findByEmail(username);

        return user.map(UserSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
