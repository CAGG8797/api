package com.example.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer id;

    @Column(name = "Name",
            nullable = false,
            length = 300)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Followers",
            joinColumns = @JoinColumn(name = "idFollower", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idUser", nullable = false)
    )
    private List<User> followers;

    public User(Integer id) {
        this.id = id;
    }

    public void addFollower(User follower){
        followers.add(follower);
    }

}
