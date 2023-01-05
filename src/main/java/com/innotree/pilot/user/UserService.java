package com.innotree.pilot.user;

import com.innotree.pilot.role.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    List<User> findEveryUser();

    Page<User> pageUser(int number);

        List<Role> findEveryRole();
//    Role findRole();
    void deleteUser(Integer id);

    User saveUser(User user);

    User getUser(Integer id);

}
