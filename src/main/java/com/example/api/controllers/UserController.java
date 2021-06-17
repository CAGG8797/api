package com.example.api.controllers;

import com.example.api.dto.UserDTO;
import com.example.api.models.User;
import com.example.api.repositories.UsersRepository;
import com.example.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService service;

    /*@GetMapping
    public List<UserDTO> getHola(){
        return (List<User>) usersRepository.findAll();
    }*/

    @PostMapping("follow/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void follow(@PathVariable("idUser") Integer idUser,
                       @RequestParam("idFollower") Integer idFollower){

        service.followUser(idUser,idFollower);

    }



}
