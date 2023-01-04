package com.innotree.pilot.user;

import com.innotree.pilot.Response.Message;
import com.innotree.pilot.Response.StatusEnum;
import com.innotree.pilot.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        List<User> listEveryUser =  userService.findEveryUser();
        model.addAttribute("ListEveryUser", listEveryUser);
        return "redirect:/Home";
    }

    @GetMapping("/CreateForm")
    public String CreateUser(Model model){
        User user = new User();
        List<Role> createUserRole = userService.findEveryRole();
        model.addAttribute("user",user);
        model.addAttribute("roleList", createUserRole);
        return "create-user-page";
    }

//    @PutMapping("/users/editSave")
//    public String EditUserSave(User user) {
//
//        try {
//            User existingUser = userRepository.findById(user.getId()).get();
//            String username = user.getUsername();
//            String password = user.getPassword();
//            if(existingUser.getPassword() !=null){
//                existingUser.setUsername(username);
//                existingUser.setPassword(password);
//            }
//
//            userService.saveUser(existingUser);
//        } catch (NoSuchElementException exception) {
//            System.out.println("해당하는 유저가 없습니다. 잘못된 요청입니다.");
//        }
//        return "redirect:/users";
//    }
    @PostMapping("/users/save-user")
    public String SaveUser(User user,RedirectAttributes redirectAttributes){
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "해당 하는 아이디가 생성되었습니다.");
        return "redirect:/users/page-user/1";
    }

    @PutMapping("/users/save-user")
    public String SaveEditUser(User user, RedirectAttributes redirectAttributes){
        user.setCreationTime(LocalDateTime.now());
        User editSave = userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "해당 하는 아이디가 수정되었습니다.");
        return "redirect:/users/page-user/1";
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

//    @DeleteMapping("/users/delete-user/{id}")
//    public String DeleteUser(@PathVariable(name = "id") Integer id, Model model,RedirectAttributes redirectAttributes){
//        userService.deleteUser(id);
//        redirectAttributes.addFlashAttribute("message", "해당 하는 아이디" + id +" 삭제되었습니다.");
//        return "redirect:/users/page-user/1";
//    }

    @GetMapping("/users/edit-user/{id}")
    public String EditUser(@PathVariable(name = "id") Integer id,Model model){
        User user = userService.getUser(id);
        List<Role> createUserRole = userService.findEveryRole();
        model.addAttribute("user",user);
        model.addAttribute("roleList", createUserRole);
        model.addAttribute("id",id);
        return "edit-user-page";
    }

    @GetMapping("/users/page-user/{pageNumber}")
    public String pageList(@PathVariable(name = "pageNumber") int pageNumber,Model model){
        Page<User> userPage = userService.pageUser(pageNumber);
        List<User> userList = userPage.getContent();
        model.addAttribute("totalPages",userPage.getTotalPages());
        model.addAttribute("ListEveryUser",userList);
        model.addAttribute("pageNow",pageNumber);
        return "user-page";

    }

}
