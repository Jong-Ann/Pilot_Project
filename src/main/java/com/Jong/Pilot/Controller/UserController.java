package com.Jong.Pilot.Controller;

import com.Jong.Pilot.Entity.Role;
import com.Jong.Pilot.Entity.User;
import com.Jong.Pilot.Repository.RoleRepository;
import com.Jong.Pilot.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String userPage(Model model){
        List<User> ListEveryUser =  userService.findEveryUser();
        model.addAttribute("ListEveryUser",ListEveryUser);
        return "UserPage";
    }

    @GetMapping("/CreateForm")
    public String CreateUser(Model model){
        User user = new User();
        List<Role> CreateUserRole = userService.findEveryRole();
        model.addAttribute("user",user);
        model.addAttribute("roleList",CreateUserRole);
        return "CreateUserPage";
    }

    @PostMapping("/users/save")
    public String SaveUser(User user){
        System.out.println(user);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String DeleteUser(@PathVariable(name = "id") Integer id, Model model){
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
