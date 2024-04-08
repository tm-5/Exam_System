package com.examBE.BackendExamSys.configs;
import com.examBE.BackendExamSys.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class UserSecurity implements UserDetails{
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserSecurity(UserEntity userEntity) {
        System.out.println(userEntity.getEmail() + " " + userEntity.getPassword());
        this.username = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.authorities = Arrays.stream(userEntity.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername(){
        return username;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
