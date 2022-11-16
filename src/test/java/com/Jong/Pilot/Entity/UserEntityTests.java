package com.Jong.Pilot.Entity;


import com.Jong.Pilot.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserEntityTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("첫번째 유저 생성 테스트입니다.")
    public void firstUserTest(){
        Role normalRole = entityManager.find(Role.class,1);
        User JongAnn = new User("JongAnn","dlwhddksiq95!");
        JongAnn.addRoles(normalRole);

        User UserJongAnn = userRepository.save(JongAnn);

        assertThat(UserJongAnn.getId()).isGreaterThan(0);

    }
    @Test
    public void testMultiRole(){
        User testUser = new User("test","test1");

        Role roleAdmin = new Role(1);
        Role roleNormal = new Role(2);

        testUser.addRoles(roleNormal);
        testUser.addRoles(roleAdmin);

        User saveTestUser = userRepository.save(testUser);

        assertThat(saveTestUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testFindEveryUser(){
        List<User> FindEveryUser = (List<User>) userRepository.findAll();
        FindEveryUser.forEach(user -> System.out.println(user));
    }

    @Test
    public void testFindById(){
        User userJong = userRepository.findById(2).get();
        System.out.println(userJong);
    }
    @Test
    public void testUpdate(){
        User userJong = userRepository.findById(1).get();
        userJong.setUsername("JJong");
        userJong.setPassword("jongann2");
        userRepository.save(userJong);
        System.out.println(userJong);
    }

    @Test
    public void testDelete(){

        Integer id = 1;
        userRepository.deleteById(1);
    }

}