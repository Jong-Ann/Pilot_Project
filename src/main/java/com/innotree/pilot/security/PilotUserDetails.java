package com.innotree.pilot.security;

import com.innotree.pilot.role.Role;
import com.innotree.pilot.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/* UserDetails는 UserDetailsService에 의해 Return 되게 된다.
DaoAuthenticationProvider 는 UserDetails의 패스워드 및 아이디를 검사하고
인증권한을 부여하게 된다.
* */
@Getter
public class PilotUserDetails implements UserDetails {
    private User user;
    public PilotUserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        List<Role> Roles = user.getRoles();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : Roles) {
//            authorities.add((GrantedAuthority) () -> role.getName());
//        }
//        return authorities;
    }
    public Integer getId(){
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
