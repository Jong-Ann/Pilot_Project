package com.innotree.pilot.user;

import com.innotree.pilot.role.Role;
import com.innotree.pilot.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    @Transactional(readOnly = true)

    public List<User> findEveryUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> pageUser(int number) {
        Pageable pageable = PageRequest.of(number-1,5);
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findEveryRole() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        User userDelete = userRepository.findById(id).get();
//        userDelete.deleteRoles(userDelete.getRoles());
        userRepository.delete(userDelete);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        String rawPassword = user.getPassword();
        String encryptPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encryptPassword);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

}
