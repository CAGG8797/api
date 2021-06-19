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
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("post/followed")
    public List<PostDTO> getPosts(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable,
                                  OAuth2Authentication authentication){

        User user = service.getUser(authentication.getPrincipal().toString());

        return service.getPostFollowed(user, pageable);

    }

    @PostMapping("post")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO postear(@RequestBody Post post,
                           OAuth2Authentication authentication){

        User user = service.getUser(authentication.getPrincipal().toString());

        return service.createPost(post, user);

    }

    @PostMapping("{idUser}/follow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void follow(@PathVariable("idUser") Integer idUser,
                       OAuth2Authentication authentication){

        service.followUser(idUser,
                service.getUser(authentication.getPrincipal().toString()).getId());

    }

}
