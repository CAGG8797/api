package com.example.api.controllers;

import com.example.api.dto.PostDTO;
import com.example.api.models.Post;
import com.example.api.models.User;
import com.example.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("{idUser}/post/followed")
    public List<PostDTO> getPosts(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable,
                                  @PathVariable("idUser") Integer idUser){

        User user = service.getUser(idUser);

        return service.getPostFollowed(user, pageable);

    }

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
