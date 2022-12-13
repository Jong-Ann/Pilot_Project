package com.innotree.pilot.reply;


import com.innotree.pilot.board.Board;
import com.innotree.pilot.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String contents;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDate createDate;

    public Reply(String contents, Board board, User user) {
        this.contents = contents;
        this.board = board;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "contents='" + contents + '\'' +
                '}';
    }
}
