package com.Jong.pilot.role;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
@Rollback(value = false)
public class roleEntityTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("관리자 역할 생성 테스트")
    public void firstRoleTest(){

        Role Admin = new Role("Admin","Admin User manage everything");
        Role adminRole = roleRepository.save(Admin);
        assertThat(adminRole.getId()).isNotNull();
    }

    @Test
    @DisplayName("일반 역할 생성 테스트")
    public void secondRoleTest(){

        Role Normal = new Role("Normal","This user has a normal privilege");
        Role normalRole = roleRepository.save(Normal);
        assertThat(normalRole.getName()).isEqualTo("Normal");
    }
}
