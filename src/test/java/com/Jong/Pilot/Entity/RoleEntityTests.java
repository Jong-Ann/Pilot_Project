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
    @DisplayName("역할 생성 테스트 : 역할은 2가지로 나뉜다. Admin , Normal Admin에 대한 생성 테스트를 한다.")
    @BeforeTestClass
    public void roleCreateTest(){

        Role roleAdmin = new Role("Admin","User has a privilege about everything");
        Role Admin = roleRepository.save(roleAdmin);
        assertThat(Admin.getRoleName()).isEqualTo("Admin");
    }

    @Test
    @DisplayName("역할 생성 테스트 : 역할은 2가지로 나뉜다. Admin , Normal에 대한 생성 테스트를 한다.")
    @BeforeTestClass
    public void NormalRoleTest(){

        Role roleNormal = new Role("Normal","User has a normal privilege");
        Role Normal = roleRepository.save(roleNormal);

        assertThat(Normal.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("아이디에 따른 역할 지우기 테스트: 역할이 갖고 있는 번호에 따라서 아이디를 지운다.")
    public void roleDeleteById(){
        Integer roleId = 1;
        roleRepository.deleteById(roleId);
    }
    








}
