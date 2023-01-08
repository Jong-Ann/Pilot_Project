package com.innotree.pilot.board;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ResponseBoard  {

    private Integer id;
    private String title;
    private String contents;
    private String photos;
    private User user;
    private Date creationTime;
    private BoarderType boarderType;
    private Integer replySize;

    public static ResponseBoard from(Board board) {
        return new ResponseBoard(
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getPhotos(),
                board.getUser(),
                board.getCreationTime(),
                board.getBoarderType(),
                board.getReplySize()
        );
    }
}
