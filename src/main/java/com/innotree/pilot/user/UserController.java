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
//            System.out.println("???????????? ????????? ????????????. ????????? ???????????????.");
//        }
//        return "redirect:/users";
//    }
//    @PostMapping("/users/save-user")
//    public String SaveUser(User user,RedirectAttributes redirectAttributes){
//        userService.saveUser(user);
//        redirectAttributes.addFlashAttribute("message", "?????? ?????? ???????????? ?????????????????????.");
//        return "redirect:/users/page-user/1";
//    }

//    @PostMapping("/users/save-user")
//    public ResponseEntity<Message> SaveUser(@RequestBody User user,RedirectAttributes redirectAttributes){
//        User savedUser = userService.saveUser(user);
//        Message message = new Message();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
//        message.setMessage("???????????? ???????????? ?????????????????????.");
//        message.setStatus(StatusEnum.OK);
//        message.setData(user);
//        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
//    }

//    @PutMapping("/users/save-user")
//    public String SaveEditUser(User user, RedirectAttributes redirectAttributes){
//        user.setCreationTime(LocalDateTime.now());
//        User editSave = userService.saveUser(user);
//        redirectAttributes.addFlashAttribute("message", "?????? ?????? ???????????? ?????????????????????.");
//        return "redirect:/users/page-user/1";
//    }


//    @DeleteMapping("/users/delete-user/{id}")
//    public String DeleteUser(@PathVariable(name = "id") Integer id, Model model,RedirectAttributes redirectAttributes){
//        userService.deleteUser(id);
//        redirectAttributes.addFlashAttribute("message", "?????? ?????? ?????????" + id +" ?????????????????????.");
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
