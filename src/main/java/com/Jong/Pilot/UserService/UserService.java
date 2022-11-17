package com.Jong.Pilot.UserService;

import com.Jong.Pilot.Entity.Role;
import com.Jong.Pilot.Entity.User;
import com.Jong.Pilot.Repository.RoleRepository;
import com.Jong.Pilot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findEveryUser(){
         return (List<User>) userRepository.findAll();
    }

    public List<Role> findEveryRole(){
        return (List<Role>) roleRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }








}
