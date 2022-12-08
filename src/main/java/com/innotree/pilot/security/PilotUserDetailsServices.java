package com.innotree.pilot.security;

import com.innotree.pilot.user.User;
import com.innotree.pilot.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/* UserDetailsService는 DaoAuthenticationProvider가 사용하는 username과 password 및 다른 특성등을
* 찾는데 사용하게 된다.
* */

public class PilotUserDetailsServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username);
        });
        return new PilotUserDetails(user);
    }
}


