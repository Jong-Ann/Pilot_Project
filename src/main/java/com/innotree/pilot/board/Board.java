package com.innotree.pilot.board;


import com.innotree.pilot.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private String photos;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Timestamp creationTime;

    @Enumerated(EnumType.STRING)
    private BoarderType boarderType;

    public Board(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }
    @Transient
    public String getImagePath(){
        return "/board-photos" + "/"+ this.photos;
    }

    @Transient
    public String downloadImagePath(){
        return this.photos;
    }

    public BoarderType getBoarderType() {
        return boarderType;
    }
    public void setBoarderType(BoarderType boarderType) {
        this.boarderType = boarderType;
    }
}
