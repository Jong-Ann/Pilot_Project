package com.Jong.Pilot.Controller;

import com.Jong.Pilot.Entity.Role;
import com.Jong.Pilot.Entity.User;
import com.Jong.Pilot.Repository.RoleRepository;
import com.Jong.Pilot.Repository.UserRepository;
import com.Jong.Pilot.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/users/editSave")
    public String EditUserSave(User user) {

        try {
            User existingUser = userRepository.findById(user.getId()).get();
            String username = user.getUsername();
            String password = user.getPassword();

            existingUser.setUsername(username);
            existingUser.setPassword(password);
            userService.saveUser(existingUser);
        } catch (NoSuchElementException exception) {
            System.out.println("해당하는 유저가 없습니다. 잘못된 요청입니다.");
        }
        return "redirect:/users";
    }
    @PostMapping("/users/save")
    public String SaveUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String DeleteUser(@PathVariable(name = "id") Integer id, Model model){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String EditUser(@PathVariable(name = "id") Integer id,Model model){
        User user = userService.getUser(id);
        List<Role> CreateUserRole = userService.findEveryRole();

        model.addAttribute("user",user);
        model.addAttribute("roleList",CreateUserRole);
        model.addAttribute("id",id);
        return "CreateUserPage";

    }

}
