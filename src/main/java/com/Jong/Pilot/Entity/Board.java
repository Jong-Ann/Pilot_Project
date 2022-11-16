package com.Jong.Pilot.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 64)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Board(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
