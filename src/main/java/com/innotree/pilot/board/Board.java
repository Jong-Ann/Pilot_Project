package com.innotree.pilot.board;


import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 64)
    private String title;

    @Lob
    private String contents;

    @Column(nullable = true)
    private String photos;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board" ,cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    @CreationTimestamp
    private Date creationTime;

    @Enumerated(EnumType.STRING)
    private BoarderType boarderType;

    public Board(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }
    @Transient
    public String getImagePath(){
        if (photos == null) {
            return "/img/Innotree.png";
        }
        return "/board-photos" + "/"+ this.photos;
    }

    @Transient
    public String downloadImagePath(){
        return this.photos;
    }

    @Transient
    public void setImageNull() {
        this.photos = null;
    }

    @Transient
    public Integer getReplySize() {
        return replyList.size();
    }

    public BoarderType getBoarderType() {
        return boarderType;
    }

    public void setBoarderType(BoarderType boarderType) {
        this.boarderType = boarderType;
    }


    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", photos='" + photos + '\'' +
                ", user=" + user +
                ", creationTime=" + creationTime +
                ", boarderType=" + boarderType +
                '}';
    }
}
