package com.Jong.pilot.user;


import com.Jong.pilot.role.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private Timestamp creationTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Role_and_User")
    private List<Role> Roles = new ArrayList<>();

    public void addRoles(Role role) {
        this.Roles.add(role);
    }

    public void deleteRoles(Role role) {
        this.Roles.remove(role);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return this.username;
    }
}
