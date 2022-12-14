package com.innotree.pilot.user;


import com.innotree.pilot.board.Board;
import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.role.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime creationTime;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "Role_and_User")
//    private List<Role> Roles = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

//    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
//    private List<Reply> replyList;
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
//    private List<Board> boards = new ArrayList<>();

//    public void addRoles(Role role) {
//        this.Roles.add(role);
//    }
//
//    public void deleteRoles(List<Role> role) {
//        this.Roles.removeAll(role);
//    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return this.username;
    }
}
