package com.example.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPost")
    private Integer id;

    @Column(name = "Content",
            nullable = false,
            length = 5000)
    @NotNull
    @Size(max = 500)
    private String content;

    @Column(name = "CreationDate",
            nullable = false)
    @NotNull
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser",
                nullable = false,
                updatable = false)
    private User user;

    @PrePersist
    private void prePersist(){
        setCreationDate(new Date());
    }

}
