package com.Jong.Pilot.UserService;

import com.Jong.Pilot.Entity.User;
import com.Jong.Pilot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findEveryUser(){
         return (List<User>) userRepository.findAll();
    }



}
