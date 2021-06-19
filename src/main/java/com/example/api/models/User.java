package com.example.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer id;

    @Column(name = "Name",
            nullable = false,
            length = 300)
    @NotNull( message = "Name must not null")
    @Size(max = 300, message = "Max length of name is 300")
    private String name;

    @Column(name = "Username",
            nullable = false,
            unique = true,
            length = 100)
    @NotNull( message = "Username must not null")
    @Size(max = 100, message = "Max length of username is 300")
    private String username;

    @Column(name = "Password",
            nullable = false,
            length = 200)
    @JsonIgnore
    private String password;

    @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "user")
    private List<Post> posts;

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
        getFollowers().add(follower);
    }

    public boolean existFollower(User follower){
        return getFollowers().contains(follower);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
