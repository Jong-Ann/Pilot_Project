package com.innotree.pilot.reply;

import com.innotree.pilot.Response.Message;
import com.innotree.pilot.Response.StatusEnum;
import com.innotree.pilot.board.Board;
import com.innotree.pilot.board.BoardService;
import com.innotree.pilot.user.User;
import com.innotree.pilot.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
public class ReplyRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardService boardService;

    @GetMapping("/delete-reply/{id}")
    public ResponseEntity<Message> deleteReply(@PathVariable(name = "id") Integer replyId) {
        boardService.deleteReply(replyId);
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        message.setMessage("성공코드");
        message.setStatus(StatusEnum.OK);
        return new ResponseEntity<Message>(message,headers,HttpStatus.OK);
    }

    @GetMapping("/response")
    public ResponseEntity<Message> getAllUsers() {
        List<User>listAll = userRepository.findAll();
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        message.setMessage("성공코드");
        message.setStatus(StatusEnum.OK);
        message.setData(listAll);
        return new ResponseEntity<Message>(message,headers,HttpStatus.OK);
    }

}
