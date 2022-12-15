package com.innotree.pilot.test;


import com.innotree.pilot.role.Role;
import com.innotree.pilot.user.User;
import com.innotree.pilot.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import java.awt.print.Pageable;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class userEntityTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;
    @Test
    @DisplayName("첫번째 유저 생성 테스트입니다.")
    public void firstUserTest() {
        Role normalRole = entityManager.find(Role.class, 1);
        User JongAnn = new User("JongAnn", "dlwhddksiq95!");
//        JongAnn.setRoles(normalRole);
        User userJongAnn = userRepository.save(JongAnn);
        assertThat(userJongAnn.getId()).isGreaterThan(0);
    }

    @Test
    public void testMultiRole() {
        User testUser = new User("test", "test1");
        Role roleAdmin = new Role(1);
        Role roleNormal = new Role(2);
//        testUser.setRoles(roleNormal);
//        testUser.setRoles(roleAdmin);
        User saveTestUser = userRepository.save(testUser);
        assertThat(saveTestUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindEveryUser() {
        List<User> findEveryUser = (List<User>) userRepository.findAll();
        findEveryUser.forEach(user -> System.out.println(user));
    }

    @Test
    public void testFindById() {
        User userJong = userRepository.findById(2).get();
        System.out.println(userJong);
    }

    @Test
    public void testUpdate() {
        User userJong = userRepository.findById(1).get();
        userJong.setUsername("JJong");
        userJong.setPassword("jongann2");
        userRepository.save(userJong);
        System.out.println(userJong);
    }

    @Test
    public void pagingTest() {
        int pageNum = 0;
        int sizeNum = 4;
        Pageable pageable = (Pageable) PageRequest.of(0, 4);
        System.out.println(pageable);
    }

    @Test
    public void UpdateUserTest() {
        Role roleNumOne = entityManager.find(Role.class, 2);
        User userJong = userRepository.findById(1).get();
//        userJong.setRoles("JJong");
//        userJong.setPassword("jongann2");
        userJong.addRoles(roleNumOne);
        userRepository.save(userJong);
        System.out.println(userJong);
    }

//    @Test
//    public void testDelete() {
//
//        Integer id = 1;
//        userRepository.deleteById(1);
//    }

//    @Test
//    public void deleteRoleTests() {
//
//        Role Admin = new Role(2);
//        Integer userNum = 2;
//        User user = userRepository.findById(userNum).get();
//        user.deleteRoles(Admin);
//        userRepository.save(user);
//        System.out.println(user);
//
//    }
//
//    @Test
//    public void deleteById() {
//        Integer id = 2;
//        User findAndDelete = userRepository.findById(id).get();
//        userRepository.delete(findAndDelete);
//    }


//    @Test
//    public void countByIdTest(){
//
//        Integer Count = userRepository.countById(2);
//        System.out.println(Count);
//    }
//    @Test
//    @DisplayName("1번 Admin 2번 Normal")
//    public void RelationMappingTest(){
//        User testUser = new User("Relation","TEST");
//        Role roleAdmin = new Role(1);
//
//        testUser.addRoles(roleAdmin);
//
//        User saveUser = userRepository.save(testUser);
//        assertThat(saveUser.getId()).isGreaterThan(0);
//    }
}
