package com.Jong.pilot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
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
    private List<Role> roles = new ArrayList<>();

    public void addRoles(Role role) {
        this.roles.add(role);
    }

    public void deleteRoles(Role role) {
        this.roles.remove(role);
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
