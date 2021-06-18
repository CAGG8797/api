package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer id;

    private String post;

    private Date date;

    private Integer idUser;

    private String nameUser;

}
