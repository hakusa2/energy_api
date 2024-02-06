package com.energy.welfare.dto.users;

import com.energy.welfare.enumeration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private String userRole;

    @Builder
    public User(String password, String userRole, String email) {
        this.password = password;
        this.userRole = userRole;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 계정의 권한 목록을 리턴
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRole));
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password; // 계정의 비밀번호 리턴
    }

    @Override
    public String getUsername() {
        return this.email; // 계정의 고유한 값 리턴
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정의 만료 여부 리턴
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정의 잠김 여부 리턴
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 비밀번호 만료 여부 리턴
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정의 활성화 여부 리턴
    }

    public String getUserRole(){
        return this.userRole;
    }
}
