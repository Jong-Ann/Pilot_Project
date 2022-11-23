package com.Jong.Pilot.Security;

import com.Jong.Pilot.Entity.User;
import com.Jong.Pilot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PilotUserDetailsServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username);
        });
        return new PilotUserDetails(principal);
    }
}


