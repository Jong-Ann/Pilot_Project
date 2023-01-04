package com.innotree.pilot.user;

import com.innotree.pilot.Response.Message;
import com.innotree.pilot.Response.StatusEnum;
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
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        message.setMessage("해당하는 아이디가 생성되었습니다.");
        message.setStatus(StatusEnum.OK);
        message.setData(user);
        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
    }

    @DeleteMapping("/users/delete-user/{id}")
    public ResponseEntity<Message> deleteUser(@PathVariable(name = "id") Integer id , Model model , RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "해당 하는 아이디" + id + " 삭제되었습니다");
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        message.setMessage("해당 하는 아이디가 삭제 되었습니다");
        message.setStatus(StatusEnum.OK);
        return new ResponseEntity<Message>(message,headers,HttpStatus.OK);
    }


}
