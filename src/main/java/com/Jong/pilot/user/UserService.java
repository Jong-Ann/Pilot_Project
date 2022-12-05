package com.Jong.pilot.user;

import com.Jong.pilot.role.Role;
import com.Jong.pilot.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findEveryUser() {
        return (List<User>) userRepository.findAll();
    }

    public Page<User> pageUser(int number){
        Pageable pageable = PageRequest.of(number-1,5);
        return userRepository.findAll(pageable);
    }

    public List<Role> findEveryRole() {
        return (List<Role>) roleRepository.findAll();
    }

    public void deleteUser(Integer id) {
        User userDelete = userRepository.findById(id).get();
        userRepository.delete(userDelete);
    }

    public void saveUser(User user) {
        String rawPassword = user.getPassword();
        String encryptPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encryptPassword);
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
