package com.example.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
