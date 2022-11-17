package com.Jong.Pilot.Entity;


import com.Jong.Pilot.Repository.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
@Rollback(value = false)
public class RoleEntityTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("관리자 역할 생성 테스트")
    public void firstRoleTest(){

        Role Admin = new Role("Admin","Admin User manage everything");
        Role AdminRole = roleRepository.save(Admin);
        assertThat(AdminRole.getId()).isNotNull();
    }

    @Test
    @DisplayName("일반 역할 생성 테스트")
    public void secondRoleTest(){

        Role Normal = new Role("Normal","This user has a normal privilege");
        Role NormalRole = roleRepository.save(Normal);
        assertThat(NormalRole.getName()).isEqualTo("Normal");
    }
}
