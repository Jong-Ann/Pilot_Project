package com.innotree.pilot.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class passwordTest {

    @Test
    public void PasswordEncodeTest(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String encodePassword = passwordEncoder.encode(rawPassword);
        boolean matches = passwordEncoder.matches(rawPassword,encodePassword);
        assertThat(matches).isTrue();
    }
}
