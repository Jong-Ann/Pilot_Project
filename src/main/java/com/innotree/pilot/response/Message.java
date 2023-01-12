package com.innotree.pilot.response;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;

@Data
public class Message {
    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public ResponseEntity<Message> successMessage() {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        message.setMessage("성공코드");
        message.setStatus(StatusEnum.OK);
        return new ResponseEntity<Message> (message,headers, HttpStatus.OK);
    }
}