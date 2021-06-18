package com.example.api.controllers;

import com.example.api.dto.PostDTO;
import com.example.api.models.Post;
import com.example.api.models.User;
import com.example.api.repositories.PostRepository;
import com.example.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("{idUser}/post")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO postear(@PathVariable("idUser") Integer idUser,
                           @RequestBody Post post){

        User user = service.getUser(idUser);

        return service.createPost(post, user);

    }

    @PostMapping("follow/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void follow(@PathVariable("idUser") Integer idUser,
                       @RequestParam("idFollower") Integer idFollower){

        service.followUser(idUser,idFollower);

    }

}
