package com.Jong.Pilot.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "roleName",length = 255)
    private String roleName;

    @Column(name = "description" , length = 255)
    private String description;

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public Role(Integer id) {
        Id = id;
    }

}
