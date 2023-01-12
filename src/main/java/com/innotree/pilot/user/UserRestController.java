package com.innotree.pilot.user;

import com.innotree.pilot.response.Message;
import com.innotree.pilot.response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.Charset;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/save-user")
    public ResponseEntity<Message> SaveUser(@RequestBody User user, RedirectAttributes redirectAttributes){
        User savedUser = userService.saveUser(user);
        return new Message().successMessage();
    }

    @DeleteMapping("/users/delete-user/{id}")
    public ResponseEntity<Message> deleteUser(@PathVariable(name = "id") Integer id , Model model , RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "해당 하는 아이디" + id + " 삭제되었습니다");
        return new Message().successMessage();
    }

    @PutMapping("/users/save-user/{userId}")
    public ResponseEntity<Message> SaveEditUser(@RequestBody User user,@PathVariable(name = "userId") Integer userId, RedirectAttributes redirectAttributes){
        User savedUser = userRepository.findById(user.getId()).get();
        savedUser.setUsername(user.getUsername());
        savedUser.setPassword(user.getPassword());
        savedUser.setRole(user.getRole());
        userRepository.save(savedUser);
        redirectAttributes.addFlashAttribute("message", "해당 하는 아이디가 수정되었습니다.");
        return new Message().successMessage();
    }

}
