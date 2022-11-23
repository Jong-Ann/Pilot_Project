package com.Jong.Pilot.Entity;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PasswordTest {

    @Test
    public void PasswordEncodeTest(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "dontgo";
        String encodePassword = passwordEncoder.encode(rawPassword);

        boolean matches = passwordEncoder.matches(rawPassword,encodePassword);
        assertThat(matches).isTrue();
    }
}
