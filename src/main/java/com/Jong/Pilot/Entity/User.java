package com.Jong.Pilot.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @JoinTable(name = "RoleAndUser" , joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public void addRoles(Role role){
        this.roles.add(role);
    }
    public void deleteRoles(Role role){
        this.roles.remove(role);
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
