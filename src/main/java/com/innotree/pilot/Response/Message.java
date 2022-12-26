package com.innotree.pilot.Response;

import lombok.Data;

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
}