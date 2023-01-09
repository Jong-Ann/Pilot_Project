package com.innotree.pilot.role;

import com.innotree.pilot.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50,nullable = false,unique = true)
    private String name;

    @Column(length = 255 , nullable = false)
    private String description;


    @CreationTimestamp
    private Timestamp creationTime;

//    @ManyToMany(mappedBy = "Roles",cascade = CascadeType.ALL)
//    private List<User> user = new ArrayList<>();
    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;




    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
