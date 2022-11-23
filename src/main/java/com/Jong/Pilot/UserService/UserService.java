package com.Jong.Pilot.UserService;

import com.Jong.Pilot.Entity.Role;
import com.Jong.Pilot.Entity.User;
import com.Jong.Pilot.Repository.RoleRepository;
import com.Jong.Pilot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findEveryUser() {
        return (List<User>) userRepository.findAll();
    }

    public List<Role> findEveryRole() {
        return (List<Role>) roleRepository.findAll();
    }

    public void deleteUser(Integer id) {

        User userDelete  = userRepository.findById(id).get();
        userRepository.delete(userDelete);

    }

    public void saveUser(User user) {
        String rawPassword = user.getPassword();
//        String encryptPassword = bCryptPasswordEncoder.encode(rawPassword);
//        user.setPassword(encryptPassword);
        userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

//    public User editUser(User user) {
//
//        User existingUser = userRepository.findById(user.getId()).get();
//
//        String username = user.getUsername();
//        String password = user.getPassword();
//
//        existingUser.setUsername(username);
//        existingUser.setPassword(password);
//
//        return existingUser;
//
//    }
}
