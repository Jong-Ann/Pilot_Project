package com.Jong.Pilot.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50 , nullable = false,unique = true)
    private String username;

    @Column(length = 255 , nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "userRole" , joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRoles(Role role){
        this.roles.add(role);
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
